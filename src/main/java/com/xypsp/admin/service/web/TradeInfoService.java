package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.TradeInfoDTO;
import com.xypsp.admin.domain.request.web.TradeInfoReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author rp
 */
public interface TradeInfoService {

    /**
     * 分页查询类目
     * @param page
     * @param pageSize
     * @param tradeName
     * @return
     */
    PageInfo<TradeInfoDTO> findByTradeInfos(Integer page, Integer pageSize, String tradeName);


    /**
     * 修改类目
     * @param tradeInfoReq
     * @return
     */
    boolean updateTradeInfo(TradeInfoReq tradeInfoReq);

    /**
     * 创建类目
     * @param tradeInfoReq
     * @return
     */
    boolean createTradeInfo(TradeInfoReq tradeInfoReq);

    /**
     * 批量删除类目
     * @param ids
     */
    void deletes(String ids);


    /**
     * 激活/关闭类目
     * @param id
     */
    void isActive(Long id);


    /**
     * 获取所有行业
     * @return
     */
    List<TradeInfoDTO> findAllTradeInfos();
}
