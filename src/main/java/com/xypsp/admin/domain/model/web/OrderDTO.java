package com.xypsp.admin.domain.model.web;

import lombok.Data;
import java.util.Date;

/**
 * @author rp
 */
@Data
public class OrderDTO {
    /**
     * 订单编号
     * */
    private String orderNo;
    /**
     *订单类型(1 置顶/ 0 推广/ 2 发布缴费)
     * */
    private Integer orderType;
    /**
     * 订单状态 0, "等待支付" 1, "支付成功" 2, "支付过期"
     * */
    private Integer orderStatus;
    /**
     *订单金额
     * */
    private Integer orderAmount;
    /**
     * 置顶天数
     * */
    private Integer stickDay;
    /**
     * 支付时间
     * */
    private Date payedAt;

    /**
     * 转让信息ID
     * */
    private Integer transferId;
    /**
     *用户ID
     * */
    private Integer userId;

    /**
     * 付款用户昵称
     * */
    private String nickname;
    /**
     * 付款用户头像
     * */
    private String avatar;
}
