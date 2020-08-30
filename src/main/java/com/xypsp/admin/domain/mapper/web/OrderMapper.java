package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author rp
 */
@Mapper
@Repository
public interface OrderMapper {


    /**
     * 统计有效销售额
     * @return
     */
    Long countSales();


    /**
     * 查询订单
     * @return
     */
    List<OrderDTO> findByOrders();


    /**
     * 模糊搜索订单
     * @param search
     * @return
     */
    List<OrderDTO> findByOrdersLike(String search);

    /**
     * 根据店铺信息查询置顶订单
     * @param transferId
     * @return
     */
    OrderDTO findByTransferIdAndOrderType(Long transferId);


}
