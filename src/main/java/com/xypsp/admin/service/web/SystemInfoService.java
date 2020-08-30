package com.xypsp.admin.service.web;

import com.xypsp.admin.domain.model.web.SystemInfoDTO;
import com.xypsp.admin.domain.request.web.SystemInfoReq;

import java.util.List;

/**
 * @author rp
 */
public interface SystemInfoService {


    /**
     * 查询推广设置
     * @return
     */
    List<SystemInfoDTO> findSystemInfo();


    /**
     * 修改系统信息
     * @return
     * @param systemInfoReq
     */
    boolean updateSystemInfo(SystemInfoReq systemInfoReq);


    /**
     * 删除
     * @param id
     */
    void delete(Long id);


    /**
     * 创建设置信息
     * @param systemInfoReq
     * @return
     */
    boolean createSystemInfo(SystemInfoReq systemInfoReq);


}
