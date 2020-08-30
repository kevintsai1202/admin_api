package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class BannerDTO {

    private Long id;

    private String url;

    private Boolean isLink;

    private Boolean isShow;

    private String linkUrl;

    private Integer sort;

}
