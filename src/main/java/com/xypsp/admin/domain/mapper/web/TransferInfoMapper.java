package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.TransferInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * @author lj on 2019/12/3 10:31
 */
@Mapper
@Repository
public interface TransferInfoMapper {

    /**
     * 统计发布信息总量
     * @return
     */
    Long countTransferInfos();

    /**
     * 通过搜索关键字统计发布信息总量
     * @param search 搜索关键字
     * @return
     */
    Long countTransferInfosLikes(String search);

    /**
     * 查询发布店铺信息
     * @return
     */
    List<TransferInfoDTO> findByTransferInfos();

    /**
     * 模糊查询发布信息
     * @return
     * @param search
     */
    List<TransferInfoDTO> findByTransferInfosLikes(String search);

    /**
     * 新增店铺信息
     * @param transferInfoReq
     * @return
     */
    Integer createTransferInfo(@Param("transferInfo") TransferInfoDTO transferInfoReq);


    /**
     * 删除详情图片
     * @param transferId
     */
    void deletes(@Param("ids") List transferId);


    /**
     * 修改店铺信息
     * @param transferInfoDTO
     * @return
     */
    Integer updateTransferInfo(@Param("transferInfo") TransferInfoDTO transferInfoDTO);


    /**
     * 查询单条店铺信息
     * @param id
     * @return
     */
    TransferInfoDTO findByTransferInfo(Long id);


    /**
     * 修改置顶天数
     * @param afterDate 置顶后的时间
     * @param isGeneralize 是否推广
     * @param transferId 店铺id
     * @return Integer
     */
    Integer updateStickDate(@Param("afterDate") Date afterDate,@Param("isGeneralize") Boolean isGeneralize, @Param("transferId") Long transferId);


    /**
     * 分页查询信息
     * @param startLimit 开始数
     * @param pageSize 每页数量
     * @return
     */
    List<TransferInfoDTO> findPageByTransferInfos(@Param("startLimit") Integer startLimit,@Param("pageSize") Integer pageSize);


    /**
     * 分页搜索信息
//     * @param startLimit 开始数
//     * @param pageSize 每页数量
     * @param search 搜索关键字
     * @return
     */
    List<TransferInfoDTO> findPageByTransferInfosLikes(@Param("search")  String search);

    /**
     * 推广设置
     * @param id 店铺id
     * @param yearAfterToday 一年后时间
     * @return
     */
    Integer switchIsGeneralize(@Param("id") Integer id,@Param("afterDate") Date yearAfterToday);


}
