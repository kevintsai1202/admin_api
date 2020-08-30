package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.StickInfoDTO;
import com.xypsp.admin.domain.request.web.StickInfoReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author rp
 */
@Mapper
@Repository
public interface StickInfoMapper {

    /**
     * 查询置顶设置信息
     * @return
     */
    List<StickInfoDTO> findStickInfos();

    /**
     * 修改置顶设置信息
     * @param stickInfoReq
     * @return
     */
    Integer updateStickInfo(@Param("stickInfo") StickInfoReq stickInfoReq);

    /**
     * 创建置顶设置信息
     * @param stickInfoReq
     * @return
     */
    Integer createStickInfo(@Param("stickInfo") StickInfoReq stickInfoReq);


    /**
     * 批量删除置顶设置信息
     * @param stickIds
     */
    void deletes(@Param("ids") List stickIds);


    /**
     * 通过天数查询置顶信息
     * @param stickDay
     * @return
     */
    Optional<StickInfoDTO> findByStickDay(Long stickDay);


}
