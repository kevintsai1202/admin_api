package com.xypsp.admin.domain.model.web;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lj on 2019/12/3 11:30
 */
@Data
public class TransferInfoDTO {
    /**
     * 转让信息ID
     */
    private Long id;
    /**
     * 图标
     */
    private String icon;
    /**
     * 省份
     * */
    private String province;
    /**
     * 城市
     * */
    private String city;
    /**
     * 街道
     * */
    private String district;

    /**
     * 行业名称
     * */
    private String tradeName;
    /**
     * 标题
     */
    private String title;
    /**
     * 面积
     */
    private Long area;
    /**
     * 转让费
     */
    private Long transferPrice;
    /**
     * 租金
     */
    private Long rent;
    /**
     *联系人
     * */
    private String linkman;
    /**
     * 联系电话
     * */
    private String phone;

    /**
     * 地址
     */
    private String address;
    /**
     * 是否置顶
     */
    private Boolean isStick;
    /**
     * 置顶结束时间
     * */
    private Date stickEndTime;
    /**
     * 是否推广
     */
    private Boolean isGeneralize;
    /**
     *推广结束时间
     * */
    private Date generalizeEndTime;
    /***
     * 到期时间
     * */
    private Date expiryTime;

    /**
     * 描述
     * */
    private String description;
    /**
     * 发布是否收费
     * */
    private Boolean isChargePublish;
    /**
     * 发布人id
     * */
    private Long userId;

    private String nickname;

    private String avatar;

    /**
     * 发布时间
     */
    private Date createdAt;

    /**
     * 置顶订单信息
     * */
    private OrderDTO order;

    /**
     * 详情图片
     * */
    private List<StoreImgDTO> storeImgS;
}