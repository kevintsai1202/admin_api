package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.BannerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BannerMapper {
    /**
     * 查询banners
     * @return
     */
    List<BannerDTO> findBanners();


    /**
     * 创建banner
     * @param banner
     * @return
     */
    Integer insert(@Param("banner") BannerDTO banner);


    /**
     * 删除banner
     * @param id
     */
    void delete(@Param("id") Long id);


    /**
     * 更新banner
     * @param banner
     * @return
     */
    Integer update(@Param("banner") BannerDTO banner);


    BannerDTO selectById(@Param("id") Long id);

    void updateShowById(@Param("id") Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deletes(@Param("ids") List ids);


    /**
     * 根据ids查询
     * @param ids
     * @return
     */
    List<BannerDTO> findBannersByIds(@Param("ids") List ids);


}
