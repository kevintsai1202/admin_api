package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author lj on 2019/12/3 11:14
 */
@Data
public class TradeInfoDTO {

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
