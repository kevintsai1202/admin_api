package com.xypsp.admin.domain.request.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class TransferInfoReq {

    private Long id;

    private String tradeName;

    private String icon;

    private String[] storeImgS;

    private String title;

    private Long area;

    private Long transferPrice;

    private Long rent;

    private String linkman;

    private String phone;

    private String[] cityDistrict;

    private String address;

    private String description;

}
