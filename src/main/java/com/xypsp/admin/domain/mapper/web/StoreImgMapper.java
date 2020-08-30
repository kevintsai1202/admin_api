package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.StoreImgDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lj on 2019/12/3 10:31
 */
@Mapper
@Repository
public interface StoreImgMapper {


    /**
     * 通过发布信息id查询图片
     * @param transferId
     * @return
     */
    List<StoreImgDTO> findByTransferInfoId(Long transferId);

    /**
     * 创建店铺详情图片信息
     * @param storeImgS
     * @param transferId
     */
    void createStoreImgs(@Param("imgs") String[] storeImgS,@Param("transferId") Long transferId);


    /**
     * 批量查询图片
     * @param transferIds
     * @return
     */
    List<StoreImgDTO> findByTransferInfoIdsIn(@Param("transferIds") List transferIds);

    /**
     * 根据店铺删除
     * @param transferId
     */
    void deletes(@Param("transferIds") List transferId);

    /**
     * 查询单个图片
     * @param storeImgId
     * @return
     */
    StoreImgDTO findById(Long storeImgId);


    /**
     * 删除单条数据
     * @param id
     */
    void delete(Long id);


}
