package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.model.web.TradeInfoDTO;
import com.xypsp.admin.domain.request.web.TradeInfoReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.TradeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rp
 */
@RestController
@RequestMapping("/trades")
public class TradeInfoController {

    private final TradeInfoService tradeInfoService;

    public TradeInfoController(TradeInfoService tradeInfoService) {
        this.tradeInfoService = tradeInfoService;
    }

    /**
     * 分页查询类目
     * */
    @GetMapping
    public ResultVO findByTradeInfos(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                     String tradeName){
        PageInfo<TradeInfoDTO> tradeInfos = tradeInfoService.findByTradeInfos(page, pageSize,tradeName);
        return ResultVO.success(tradeInfos);
    }

    /**
     * 获取所有行业
     * */
    @GetMapping("/findAllTradeInfos")
    public ResultVO findAllTradeInfos(){
        List<TradeInfoDTO> tradeInfos = tradeInfoService.findAllTradeInfos();
        return ResultVO.success(tradeInfos);
    }

    /**
     * 修改类目
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO updateTradeInfo(@RequestBody TradeInfoReq tradeInfoReq){
        boolean updateSuccess = tradeInfoService.updateTradeInfo(tradeInfoReq);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRADE_INFO_ERROR);
    }

    /**
     * 创建类目
     * */
    @PostMapping
    public ResultVO createTradeInfo(@RequestBody TradeInfoReq tradeInfoReq){
        boolean createSuccess = tradeInfoService.createTradeInfo(tradeInfoReq);
        return createSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRADE_INFO_ERROR);
    }

    /**
     * 激活/关闭类目
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PatchMapping("/{id}")
    public ResultVO isActive(@PathVariable Long id){
        tradeInfoService.isActive(id);
        return ResultVO.success();
    }

    /**
     * 批量删除类目
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_DELETE_USER','ROLE_SUPER_ADMIN')")
    @DeleteMapping
    public ResultVO deletes(@RequestParam String ids){
        tradeInfoService.deletes(ids);
        return ResultVO.success();
    }



}
