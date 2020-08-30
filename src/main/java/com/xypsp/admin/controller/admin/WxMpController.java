package com.xypsp.admin.controller.admin;

import com.xypsp.admin.domain.request.admin.AdminBindReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.service.admin.AdminUserService;
import org.springframework.web.bind.annotation.*;

/**
 * 微信公众号相关
 * @author rp
 */
@RestController
@RequestMapping("/wxOpen")
public class WxMpController {

    private final AdminUserService adminUserService;

    public WxMpController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    /**
     * 网站扫码登录
     * */
    @GetMapping("/authQrCode")
    public ResultVO authQrCode(@RequestParam("code") String code){
        String token = adminUserService.authQrCode(code);
        return ResultVO.success(token);
    }

    /**
     * 绑定管理账号
     * */
    @PostMapping("/BindingWx")
    public ResultVO bindingWx(@RequestBody AdminBindReq adminBindReq){
        String token = adminUserService.bindingWx(adminBindReq);
        return ResultVO.success(token);
    }


}
