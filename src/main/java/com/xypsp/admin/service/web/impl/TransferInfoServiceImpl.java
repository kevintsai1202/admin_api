package com.xypsp.admin.service.web.impl;

import com.xypsp.admin.domain.mapper.web.StoreImgMapper;
import com.xypsp.admin.domain.mapper.web.TransferInfoMapper;
import com.xypsp.admin.domain.model.web.StoreImgDTO;
import com.xypsp.admin.domain.model.web.TransferInfoDTO;
import com.xypsp.admin.domain.request.web.StickDay;
import com.xypsp.admin.domain.request.web.SynDataReq;
import com.xypsp.admin.domain.request.web.TransferInfoReq;
import com.xypsp.admin.service.web.TransferInfoService;
import com.xypsp.admin.utils.DateUtils;
import com.xypsp.admin.utils.qiniu.ImageScalaKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rp
 */
@Service
public class TransferInfoServiceImpl implements TransferInfoService {

    private final ImageScalaKit imageScalaKit;

    private final StoreImgMapper storeImgMapper;

    private final TransferInfoMapper transferInfoMapper;

    public TransferInfoServiceImpl(ImageScalaKit imageScalaKit, StoreImgMapper storeImgMapper, TransferInfoMapper transferInfoMapper) {
        this.imageScalaKit = imageScalaKit;
        this.storeImgMapper = storeImgMapper;
        this.transferInfoMapper = transferInfoMapper;
    }


    @Override
    public PageInfo<TransferInfoDTO> findByTransferInfos(Integer page, Integer pageSize, String search) {
        List<TransferInfoDTO> transferInfoDTOS;
        Long aLong;
        Integer startLimit = (page - 1) * pageSize;
        if (StringUtils.isEmpty(search)){
            transferInfoDTOS = transferInfoMapper.findPageByTransferInfos(startLimit,pageSize);
            aLong = transferInfoMapper.countTransferInfos();
        }else{
            PageHelper.startPage(page,pageSize);
            transferInfoDTOS = transferInfoMapper.findPageByTransferInfosLikes(search);
            return new PageInfo<>(transferInfoDTOS);
        }
        PageInfo<TransferInfoDTO> pageInfo = new PageInfo<>(transferInfoDTOS);
        //设置总数量/当前页
        pageInfo.setPageNum(pageSize);
        pageInfo.setTotal(aLong);
        return pageInfo;
    }

    @Override
    public List<TransferInfoDTO> findAll(String search) {
        List<TransferInfoDTO> transferInfoDTOS;
        if (StringUtils.isEmpty(search)){
            transferInfoDTOS = transferInfoMapper.findByTransferInfos();
        }else{
            transferInfoDTOS = transferInfoMapper.findByTransferInfosLikes(search);
        }
        return transferInfoDTOS;
    }

    @Override
    public boolean switchIsGeneralize(Integer id) {
        Integer row = transferInfoMapper.switchIsGeneralize(id, DateUtils.getYearAfterToday());
        return row > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createTransferInfo(TransferInfoReq transferInfoReq) {
        TransferInfoDTO transferInfoDTO = getTransferInfoDTO(transferInfoReq);
        Integer row = transferInfoMapper.createTransferInfo(transferInfoDTO);
        if (row >0){
            //创建详情
            storeImgMapper.createStoreImgs(transferInfoReq.getStoreImgS(),transferInfoDTO.getId());
        }
        return row > 0;
    }

    @Override
    public void deletes(String ids) {
        String[] split = ids.split(",");
        List transferIds = Arrays.asList(split);
        //删除详情图片
//        List<StoreImgDTO> storeImgDTOS = storeImgMapper.findByTransferInfoIdsIn(transferIds);
//        for (StoreImgDTO storeImg : storeImgDTOS) {
//            try {
//                URL url = new URL(storeImg.getImgUrl());
//                imageScalaKit.delete(url.getPath().replaceFirst("/",""));
//            } catch (Exception ignored) {}
//        }
        storeImgMapper.deletes(transferIds);
        //删除店铺信息
        transferInfoMapper.deletes(transferIds);
    }

    @Override
    public boolean updateTransferInfo(TransferInfoReq tradeInfoReq) {
//        TransferInfoDTO transferInfo = transferInfoMapper.findByTransferInfo(tradeInfoReq.getId());
//        if (!transferInfo.getIcon().equals(tradeInfoReq.getIcon())){
//            try {
//                URL url = new URL(transferInfo.getIcon());
//                imageScalaKit.delete(url.getPath().replaceFirst("/",""));
//            } catch (Exception ignored) {}
//        }
        TransferInfoDTO transferInfoDTO = getTransferInfoDTO(tradeInfoReq);
        Integer row = transferInfoMapper.updateTransferInfo(transferInfoDTO);
        if (row >0){
            //创建详情
            if (tradeInfoReq.getStoreImgS().length != 0){
                storeImgMapper.createStoreImgs(tradeInfoReq.getStoreImgS(),transferInfoDTO.getId());
            }
        }
        return row > 0;
    }

    private TransferInfoDTO getTransferInfoDTO(TransferInfoReq tradeInfoReq) {
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        BeanUtils.copyProperties(tradeInfoReq, transferInfoDTO);
        String[] cityDistrict = tradeInfoReq.getCityDistrict();
        if (cityDistrict.length < 3) {
            transferInfoDTO.setProvince(cityDistrict[0]);
            transferInfoDTO.setCity(cityDistrict[0]);
            transferInfoDTO.setDistrict(cityDistrict[1]);
        } else {
            transferInfoDTO.setProvince(cityDistrict[0]);
            transferInfoDTO.setCity(cityDistrict[1]);
            transferInfoDTO.setDistrict(cityDistrict[2]);
        }
        transferInfoDTO.setExpiryTime(DateUtils.getYearAfterToday());
        transferInfoDTO.setGeneralizeEndTime(DateUtils.getYearAfterToday());
        return transferInfoDTO;
    }

    @Override
    public void delete(Long storeImgId) {
        StoreImgDTO storeImgDTO = storeImgMapper.findById(storeImgId);
//        try {
//            URL url = new URL(storeImgDTO.getImgUrl());
//            imageScalaKit.delete(url.getPath().replaceFirst("/",""));
//        } catch (Exception ignored) {
//        }
        storeImgMapper.delete(storeImgDTO.getId());
    }

    @Override
    public void synTransferData(SynDataReq synDataReq) {
        for (String sId: synDataReq.getIds()) {
            TransferInfoDTO transferInfoDTO = transferInfoMapper.findByTransferInfo(Long.valueOf(sId));
            for (String synData : synDataReq.getSynData()) {
                String[] cityData = synData.split("-");
//                if (transferInfoDTO.getAddress().equals(transferInfoDTO.getDistrict())){
                    transferInfoDTO.setAddress(cityData[2]);
//                }
                transferInfoDTO.setProvince(cityData[0]);
                transferInfoDTO.setCity(cityData[1]);
                transferInfoDTO.setDistrict(cityData[2]);
                Integer row = transferInfoMapper.createTransferInfo(transferInfoDTO);
                if (row >0){
                    //创建详情
                    List<String> collect = transferInfoDTO.getStoreImgS().stream().map(StoreImgDTO::getImgUrl).collect(Collectors.toList());
                    storeImgMapper.createStoreImgs(collect.toArray(new String[collect.size()]),transferInfoDTO.getId());
                }
            }
        }
    }

    @Override
    public boolean updateStickDate(StickDay stickDay) {
        if (stickDay.getStickDay() <= 0){
            return true;
        }
        TransferInfoDTO transferInfo = transferInfoMapper.findByTransferInfo(stickDay.getTransferId());
        Date afterDate;
        if (!StringUtils.isEmpty(transferInfo.getIsStick()) && transferInfo.getIsStick()){
            afterDate = DateUtils.getDateAddDays(transferInfo.getStickEndTime(),stickDay.getStickDay());
        }else{
            afterDate = DateUtils.getAfterDays(stickDay.getStickDay());
        }
        Integer row = transferInfoMapper.updateStickDate(afterDate,transferInfo.getIsGeneralize(),stickDay.getTransferId());
        return row > 0;
    }


}








