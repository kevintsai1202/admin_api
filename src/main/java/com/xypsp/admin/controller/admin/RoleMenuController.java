package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.model.admin.RoleMenuDTO;
import com.xypsp.admin.domain.model.admin.TreeRoleMenuDTO;
import com.xypsp.admin.domain.request.admin.MenuReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.RoleMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单相关
 * @author rp
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    /**
     * 菜单选择
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_SELECT','ROLE_SUPER_ADMIN','MENU_ADMIN_USER')")
    @GetMapping("/getMenus")
    public ResultVO getMenus(){
        List<TreeRoleMenuDTO> treeRoleMenus = roleMenuService.findRoleMenus();
        return ResultVO.success(treeRoleMenus);
    }

    /**
     * 菜单列表(目录/菜单)
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_SELECT','ROLE_SUPER_ADMIN','MENU_ADMIN_USER')")
    @GetMapping
    public ResultVO findByRoleMenus(String search){
        List<RoleMenuDTO> roleMenus = roleMenuService.findByRoleMenus(search);
        return ResultVO.success(roleMenus);
    }

    /**
     * 修改菜单
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_UPDATE','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO updateMenu(@RequestBody MenuReq menuReq){
        boolean updateSuccess = roleMenuService.updateMenu(menuReq);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.PERMISSION_UPDATE_ERROR);
    }


    /**
     * 删除菜单
     * */
    @PreAuthorize("hasAnyAuthority('SRTTING_DELETE','ROLE_SUPER_ADMIN')")
    @DeleteMapping
    public ResultVO delete(@RequestParam String id){
        roleMenuService.deleteById(id);
        return ResultVO.success();
    }

    /**
     * 新增菜单
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_ADD','ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResultVO creatMenu(@RequestBody MenuReq menuReq){
        boolean createSuccess = roleMenuService.creatMenu(menuReq);
        return createSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.PERMISSION_CREATE_ERROR);
    }
}
