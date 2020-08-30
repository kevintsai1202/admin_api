package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class StickInfoDTO {

    private Long id;
    /**
     * 置顶天数
     * */
    private Long stickDay;
    /**
     * 置顶价格
     * */
    private Long stickPrice;


}
