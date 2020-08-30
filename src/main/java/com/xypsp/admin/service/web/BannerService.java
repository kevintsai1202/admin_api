package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.BannerDTO;
import com.xypsp.admin.domain.request.web.BannerReq;
import com.github.pagehelper.PageInfo;

/**
 * @author rp
 */
public interface BannerService {
    /**
     * 查询所有banner
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<BannerDTO> findBanners(Integer page, Integer pageSize);

    /**
     * 单个删除
     * @param id
     */
    void delete(Long id);

    boolean create(BannerReq req);

    /**
     * 修改banner
     * @param req
     * @return
     */
    boolean update(BannerReq req);

    BannerDTO selectById(Long id);

    void updateShowById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deletes(String ids);

}
