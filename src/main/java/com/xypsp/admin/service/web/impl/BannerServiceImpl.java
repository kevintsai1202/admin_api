package com.xypsp.admin.service.web.impl;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.mapper.web.BannerMapper;
import com.xypsp.admin.domain.model.web.BannerDTO;
import com.xypsp.admin.domain.request.web.BannerReq;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.BannerService;
import com.xypsp.admin.utils.qiniu.ImageScalaKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author rp
 */
@Service
public class BannerServiceImpl implements BannerService {

    private final ImageScalaKit imageScalaKit;

    private final BannerMapper bannerMapper;

    public BannerServiceImpl(ImageScalaKit imageScalaKit, BannerMapper bannerMapper) {
        this.imageScalaKit = imageScalaKit;
        this.bannerMapper = bannerMapper;
    }

    @Override
    public PageInfo<BannerDTO> findBanners(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<BannerDTO> banners = bannerMapper.findBanners();
        return new PageInfo<>(banners);
    }

    @Override
    public void delete(Long id) {
        BannerDTO bannerDTO = bannerMapper.selectById(id);
        try {
            URL url = new URL(bannerDTO.getUrl());
            imageScalaKit.delete(url.getPath().replaceFirst("/",""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        bannerMapper.delete(id);
    }

    @Override
    public boolean create(BannerReq req) {
        Integer row = bannerMapper.insert(req.transformToDTO());
        return row > 0;
    }

    @Override
    public boolean update(BannerReq req) {
        Integer row = bannerMapper.update(req.transformToDTO());
        return row > 0;
    }

    @Override
    public BannerDTO selectById(Long id) {
        BannerDTO banner = bannerMapper.selectById(id);
        if (banner == null) {
            throw new CustomizeException(ResponseEnum.RESOURCE_NOT_FOUND);
        }
        return banner;
    }

    @Override
    public void updateShowById(Long id) {
        bannerMapper.updateShowById(id);
    }

    @Override
    public void deletes(String ids) {
        String[] split = ids.split(",");
        List bannerIds= Arrays.asList(split);
        deleteBanners(bannerIds);
        bannerMapper.deletes(bannerIds);
    }

    @Async
    void deleteBanners(List bannerIds) {
        List<BannerDTO> bannerDTOS = bannerMapper.findBannersByIds(bannerIds);
        for (BannerDTO banner : bannerDTOS) {
            try {
                URL url = new URL(banner.getUrl());
                imageScalaKit.delete(url.getPath().replaceFirst("/",""));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

}
