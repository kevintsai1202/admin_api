package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.model.admin.RoleDTO;
import com.xypsp.admin.domain.request.admin.RoleReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色相关
 * @author rp
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 查询所有角色
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_SELECT','ROLE_SUPER_ADMIN','ROLE_ADMIN_USER')")
    @GetMapping("/findAll")
    public ResultVO findAll(){
        List<RoleDTO> roles = roleService.findAll();
        return ResultVO.success(roles);
    }

    /**
     * 角色列表
     */
    @PreAuthorize("hasAnyAuthority('SETTING_SELECT','ROLE_SUPER_ADMIN','ROLE_ADMIN_USER')")
    @GetMapping
    public ResultVO findByRoles(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                String search) {
        PageInfo<RoleDTO> roles = roleService.findByRoles(page, pageSize, search);
        return ResultVO.success(roles);
    }

    /**
     * 修改角色
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_UPDATE','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO updateRole(@RequestBody RoleReq roleReq){
        boolean updateSuccess = roleService.updateRole(roleReq);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.PERMISSION_UPDATE_ERROR);
    }


    /**
     * 删除角色
     * */
    @PreAuthorize("hasAnyAuthority('SRTTING_DELETE','ROLE_SUPER_ADMIN')")
    @DeleteMapping
    public ResultVO deletes(@RequestParam String ids){
        roleService.deletes(ids);
        return ResultVO.success();
    }

    /**
     * 新增角色
     */
    @PreAuthorize("hasAnyAuthority('SETTING_ADD','ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResultVO createRole(@RequestBody RoleReq roleReq) {
        boolean createSuccess = roleService.createRole(roleReq);
        return createSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.PERMISSION_CREATE_ERROR);
    }

}
