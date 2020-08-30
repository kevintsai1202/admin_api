package com.xypsp.admin.domain.request.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class SystemInfoReq {

    private Long id;

    private Long seekAmount;

    private Long generalizePrice;

    private Boolean isChargePublish = false;

    private Integer publishMoney = 0;
}
