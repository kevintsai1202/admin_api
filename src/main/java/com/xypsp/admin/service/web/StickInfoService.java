package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.StickInfoDTO;
import com.xypsp.admin.domain.request.web.StickInfoReq;
import com.github.pagehelper.PageInfo;

/**
 * @author rp
 */
public interface StickInfoService {

    /**
     * 查询置顶设置信息
     * @return
     * @param page
     * @param pageSize
     */
    PageInfo<StickInfoDTO> findStickInfos(Integer page, Integer pageSize);

    /**
     * 修改置顶设置信息
     * @param stickInfoReq
     * @return
     */
    boolean updateStickInfo(StickInfoReq stickInfoReq);


    /**
     * 创建置顶设置信息
     * @param stickInfoReq
     * @return
     */
    boolean createStickInfo(StickInfoReq stickInfoReq);


    /**
     * 批量删除置顶设置信息
     * @param ids
     */
    void deletes(String ids);


}
