package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class SystemInfoDTO {

    private Long id;

    private Long seekAmount;

    private Long generalizePrice;

    /**
     * 发布是否收费
     * */
    private Boolean isChargePublish;
    /**
     * 发布费用
     * */
    private Integer publishMoney;

}
