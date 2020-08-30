package com.xypsp.admin.domain.request.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class TradeInfoReq {

    /**
     * 类目ID
     */
    private Long id;

    /**
     * 类目名称(行业名称)
     */
    private String tradeName;

    /**
     * 是否启用
     * */
    private Boolean isActive;

    /**
     * 排序
     * */
    private Integer sort;
}
