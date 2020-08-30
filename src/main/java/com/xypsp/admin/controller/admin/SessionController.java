package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.model.admin.AdminUserDTO;
import com.xypsp.admin.domain.request.admin.AdminUserInfoReq;
import com.xypsp.admin.domain.request.admin.AdminUserPassReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.AdminUserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * 管理员相关
 * @author rp
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    private final AdminUserService adminUserService;

    public SessionController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    /**
     * 获取用户信息
     * */
    @GetMapping
    public ResultVO index(Principal principal){
        AdminUserDTO adminUserDTO = adminUserService.findByUsername(principal.getName()).get();
        return ResultVO.success(adminUserDTO);
    }

    /**
     * 查询用户是否存在
     * */
    @GetMapping("/findByUsername")
    public ResultVO findByUsername(@RequestParam String username){
        Optional<AdminUserDTO> userDTO =  adminUserService.findByUsername(username);
        if (!userDTO.isPresent()){
            throw new CustomizeException(ResponseEnum.USER_NOT_FOUND);
        }
        return ResultVO.success();
    }

    /**
     * 更新用户信息
     * */
    @PatchMapping("/update")
    public ResultVO update(@RequestBody AdminUserInfoReq adminUserInfoReq, Principal principal){
        AdminUserDTO adminUserDTO = adminUserService.update(adminUserInfoReq,principal.getName());
        return ResultVO.success(adminUserDTO);
    }

    /**
     * 修改密码
     * */
    @PatchMapping("/updatePass")
    public ResultVO updatePass(@RequestBody AdminUserPassReq adminUserPassReq){
        String token = adminUserService.updatePass(adminUserPassReq);
        return ResultVO.success(token);
    }

}
