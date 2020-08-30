package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.OrderDTO;
import com.github.pagehelper.PageInfo;

/**
 * @author rp
 */
public interface OrderService {


    /**
     * 分页查询订单
     * @param page
     * @param pageSize
     * @param search
     * @return
     */
    PageInfo<OrderDTO> findByOrders(Integer page, Integer pageSize, String search);


}
