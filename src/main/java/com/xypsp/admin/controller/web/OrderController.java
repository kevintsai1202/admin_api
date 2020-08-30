package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.model.web.OrderDTO;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.service.web.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rp
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 分页查询订单
     * */
    @GetMapping
    public ResultVO findByOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                 String search){
        PageInfo<OrderDTO> orders = orderService.findByOrders(page,pageSize,search);
        return ResultVO.success(orders);
    }


}
