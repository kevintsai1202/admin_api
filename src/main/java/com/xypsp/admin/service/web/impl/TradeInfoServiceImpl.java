package com.xypsp.admin.service.web.impl;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.mapper.web.TradeInfoMapper;
import com.xypsp.admin.domain.model.web.TradeInfoDTO;
import com.xypsp.admin.domain.request.web.TradeInfoReq;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.TradeInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author rp
 */
@Service
public class TradeInfoServiceImpl implements TradeInfoService {

    private final TradeInfoMapper tradeInfoMapper;

    public TradeInfoServiceImpl(TradeInfoMapper tradeInfoMapper) {
        this.tradeInfoMapper = tradeInfoMapper;
    }

    @Override
    public PageInfo<TradeInfoDTO> findByTradeInfos(Integer page, Integer pageSize, String tradeName) {
        PageHelper.startPage(page,pageSize);
        List<TradeInfoDTO> tradeInfoDTOS;
        if (StringUtils.isEmpty(tradeName)){
            tradeInfoDTOS = tradeInfoMapper.findByTradeInfos();
        }else{
            tradeInfoDTOS = tradeInfoMapper.findByTradeNameLike(tradeName);
        }
        return new PageInfo<>(tradeInfoDTOS);
    }

    @Override
    public boolean updateTradeInfo(TradeInfoReq tradeInfoReq) {
        Optional<TradeInfoDTO> optional = tradeInfoMapper.findByTradeName(tradeInfoReq.getTradeName());
        if (optional.isPresent()){
            throw new CustomizeException(ResponseEnum.TRADE_INFO_NAME_EXIST);
        }
        Integer row = tradeInfoMapper.updateTradeInfo(tradeInfoReq);
        return row > 0;
    }

    @Override
    public boolean createTradeInfo(TradeInfoReq tradeInfoReq) {
        Optional<TradeInfoDTO> optional = tradeInfoMapper.findByTradeName(tradeInfoReq.getTradeName());
        if (optional.isPresent()){
            throw new CustomizeException(ResponseEnum.TRADE_INFO_NAME_EXIST);
        }
        Integer row = tradeInfoMapper.createTradeInfo(tradeInfoReq);
        return row > 0;
    }

    @Override
    public void deletes(String ids) {
        String[] split = ids.split(",");
        List tradeIds= Arrays.asList(split);
        tradeInfoMapper.deletes(tradeIds);
    }

    @Override
    public void isActive(Long id) {
        tradeInfoMapper.isActive(id);
    }

    @Override
    public List<TradeInfoDTO> findAllTradeInfos() {
        return tradeInfoMapper.findByTradeInfos();
    }
}
