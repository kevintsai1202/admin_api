
package com.xypsp.admin.domain.request.web;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author rp
 */
@Data
public class OrderVO {

    /**
     * 订单
     * */
    private String orderId;
    /**
     * 0 代付款
     * 1 待确认
     * 2 完结
     * 3 已失效
     * */
    private Integer orderStatus;
    private Long buyerId;
    private Long sellerId;
    private BigDecimal orderAmount;
    /**
     * 订单详情
     * */
    private Long albumId;
    private String albumName;
    private String albumIcon;
    private BigDecimal albumPrice;
    private Integer albumQuantity;
}


