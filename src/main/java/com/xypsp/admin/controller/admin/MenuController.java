package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.model.admin.RoleMenuDTO;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.service.admin.AdminUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author rp
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


    private final AdminUserService adminUserService;

    public MenuController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    /**
     * 获取管理员权限菜单
     * */
    @GetMapping
    public ResultVO findByMenus(Principal principal){
        List<RoleMenuDTO> menus = adminUserService.findByMenus(principal.getName());
        return ResultVO.success(menus);
    }


}
