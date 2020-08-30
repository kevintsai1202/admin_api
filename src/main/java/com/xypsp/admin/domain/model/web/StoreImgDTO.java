package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author lj on 2019/12/3 11:30
 */
@Data
public class StoreImgDTO {

    private Long id;
    /**
     * 图片地址
     * */
    private String imgUrl;
    /**
     * 转让信息id
     * */
    private Long transferId;

}