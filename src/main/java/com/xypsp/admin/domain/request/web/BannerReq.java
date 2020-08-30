package com.xypsp.admin.domain.request.web;

import com.xypsp.admin.domain.model.web.BannerDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author rp
 */
@Data
public class BannerReq {

    private Long id;

    private String url;

    private Boolean isLink;

    private String linkUrl;

    private Integer sort;

    private Boolean isShow;

    public BannerDTO transformToDTO() {
        BannerDTO dto = new BannerDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
