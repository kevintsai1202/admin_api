package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.SystemInfoDTO;
import com.xypsp.admin.domain.request.web.SystemInfoReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lj on 2019/11/20 15:14
 */
@Mapper
@Repository
public interface SystemInfoMapper {


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
    Integer updateSystemInfo(@Param("systemInfo") SystemInfoReq systemInfoReq);


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
    Integer createSystemInfo(@Param("systemInfo") SystemInfoReq systemInfoReq);

}
