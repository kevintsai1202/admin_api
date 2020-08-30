package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.TransferInfoDTO;
import com.xypsp.admin.domain.request.web.StickDay;
import com.xypsp.admin.domain.request.web.SynDataReq;
import com.xypsp.admin.domain.request.web.TransferInfoReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author rp
 */
public interface TransferInfoService {


    /**
     * 分页查询发布店铺信息
     * @param page
     * @param pageSize
     * @param search
     * @return
     */
    PageInfo<TransferInfoDTO> findByTransferInfos(Integer page, Integer pageSize, String search);


    /**
     * 新增店铺信息
     * @param tradeInfoReq
     * @return
     */
    boolean createTransferInfo(TransferInfoReq tradeInfoReq);


    /**
     * 批量删除店铺信息
     * @param ids
     */
    void deletes(String ids);


    /**
     * 修改店铺信息
     * @param tradeInfoReq
     * @return
     */
    boolean updateTransferInfo(TransferInfoReq tradeInfoReq);


    /**
     * 修改时单个删除店铺图片信息
     * @param imgUrl
     */
    void delete(Long imgUrl);


    /**
     * 同步店铺信息到其它城市
     * @param synDataReq 同步数据
     */
    void synTransferData(SynDataReq synDataReq);


    /**
     * 修改置顶天数
     * @param stickDay 置顶信息
     * @return
     */
    boolean updateStickDate(StickDay stickDay);


    /**
     * 查询所有
     * @param search 搜索内容
     * @return transfers
     */
    List<TransferInfoDTO> findAll(String search);


    /**
     * 推广开关
     * @param id 店铺id
     * @return
     */
    boolean switchIsGeneralize(Integer id);


}
