package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.domain.response.WelcomeVO;
import com.xypsp.admin.service.web.WelcomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renpei
 */
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    private final WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    /**
     * 统计
     * */
    @GetMapping
    public ResultVO welcome() {
        WelcomeVO welcomeVO = welcomeService.welcomeCount();
        return ResultVO.success(welcomeVO);
    }
}
