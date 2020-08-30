package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.model.admin.AdminUserDTO;
import com.xypsp.admin.domain.request.admin.AdminUserPermissionDTO;
import com.xypsp.admin.domain.request.admin.AdminUserReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.service.admin.AdminUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * 管理员相关
 * @author rp
 */
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    /**
     * 管理员列表
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_SELECT','ROLE_SUPER_ADMIN','ACCOUNT_ADMIN_USER')")
    @GetMapping
    public ResultVO findByAdminUsers(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                     String search){
        PageInfo<AdminUserDTO> adminUsers = adminUserService.findByAdminUsers(page, pageSize,search);
        return ResultVO.success(adminUsers);
    }

    /**
     * 修改管理员
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_UPDATE','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO updateAdminUserInfo(@RequestBody AdminUserReq adminUserReq){
        adminUserService.updateAdminUserInfo(adminUserReq);
        return ResultVO.success();
    }

    /**
     * 修改管理员权限
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_UPDATE','ROLE_SUPER_ADMIN')")
    @PatchMapping
    public ResultVO patchAdminUserPermission(@RequestBody AdminUserPermissionDTO adminUserPermissionDTO){
        adminUserService.patchAdminUserPermission(adminUserPermissionDTO);
        return ResultVO.success();
    }

    /**
     * 批量删除管理员
     * */
    @PreAuthorize("hasAnyAuthority('SRTTING_DELETE','ROLE_SUPER_ADMIN')")
    @DeleteMapping
    public ResultVO deletes(@RequestParam String ids){
        adminUserService.deletes(ids);
        return ResultVO.success();
    }

    /**
     * 新增管理员·关联角色
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_ADD','ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResultVO createAdminUser(@RequestBody AdminUserReq adminUserReq){
        adminUserService.createAdminUser(adminUserReq);
        return ResultVO.success();
    }

    /**
     * 禁用/启用
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_UPDATE','ROLE_SUPER_ADMIN')")
    @PatchMapping("/{id}")
    public ResultVO switchIsBan(@PathVariable Integer id){
        adminUserService.switchIsBan(id);
        return ResultVO.success();
    }


}
