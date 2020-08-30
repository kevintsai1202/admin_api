package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.TradeInfoDTO;
import com.xypsp.admin.domain.request.web.TradeInfoReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author rp
 */
@Mapper
@Repository
public interface TradeInfoMapper {

    /**
     * 分页查询类目
     * @return
     */
    List<TradeInfoDTO> findByTradeInfos();

    /**
     * 修改类目
     * @param tradeInfoReq
     * @return
     */
    Integer updateTradeInfo(@Param("tradeInfo") TradeInfoReq tradeInfoReq);

    /**
     * 通过类目名查询类目
     * @param tradeName
     * @return
     */
    Optional<TradeInfoDTO> findByTradeName(String tradeName);

    /**
     * 创建类目
     * @param tradeInfoReq
     * @return
     */
    Integer createTradeInfo(@Param("tradeInfo")TradeInfoReq tradeInfoReq);

    /**
     * 批量删除类目
     * @param ids
     */
    void deletes(@Param("ids") List ids);

    /**
     * 激活/关闭类目
     * @param id
     */
    void isActive(Long id);


    /**
     * 通过行业模糊查询
     * @param tradeName
     * @return
     */
    List<TradeInfoDTO> findByTradeNameLike(String tradeName);


}
