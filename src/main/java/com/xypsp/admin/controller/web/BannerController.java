package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.model.web.BannerDTO;
import com.xypsp.admin.domain.request.web.BannerReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.BannerService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author rp
 */
@RestController
@RequestMapping("/banners")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }


    /**
     * 分页查询banners
     * */
    @GetMapping
    public ResultVO bannerIndex(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize) {
        PageInfo<BannerDTO> banners = bannerService.findBanners(page, pageSize);
        return ResultVO.success(banners);
    }


    /**
     * 新增
     * */
    @PostMapping
    public ResultVO bannerCreate(@RequestBody BannerReq req) {
        boolean createSuccess = bannerService.create(req);
        return createSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.BANNER_CREATE_FAIL);
    }


    /**
     * 修改
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO bannerUpdate(@RequestBody BannerReq req) {
        boolean updateSuccess = bannerService.update(req);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.BANNER_CREATE_FAIL);
    }


    /**
     * 修改回填
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @GetMapping("/{id}/edit")
    public ResultVO bannerEdit(@PathVariable("id") Long id) {
        BannerDTO banner = bannerService.selectById(id);
        return ResultVO.success(banner);
    }


    /**
     * 修改展示/隐藏
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @GetMapping("/updateShow/{id}")
    public ResultVO bannerShow(@PathVariable("id") Long id) {
        bannerService.updateShowById(id);
        return ResultVO.success();
    }

    /**
     * 单个删除
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_DELETE_USER','ROLE_SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResultVO bannerDelete(@PathVariable("id") Long id) {
        bannerService.delete(id);
        return ResultVO.success();
    }

    /**
     * 批量删除
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_DELETE_USER','ROLE_SUPER_ADMIN')")
    @DeleteMapping("/deletes")
    public ResultVO bannerDeletes(@RequestParam String ids) {
        bannerService.deletes(ids);
        return ResultVO.success();
    }

}
