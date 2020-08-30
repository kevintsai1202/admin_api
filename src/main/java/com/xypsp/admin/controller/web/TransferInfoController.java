package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.model.web.TransferInfoDTO;
import com.xypsp.admin.domain.request.web.StickDay;
import com.xypsp.admin.domain.request.web.SynDataReq;
import com.xypsp.admin.domain.request.web.TransferInfoReq;
import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.TransferInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rp
 */
@RestController
@RequestMapping("/transfers")
public class TransferInfoController {

    private final TransferInfoService transferInfoService;

    public TransferInfoController(TransferInfoService transferInfoService) {
        this.transferInfoService = transferInfoService;
    }

    /**
     * 分页查询发布店铺信息
     * */
    @GetMapping
    public ResultVO findByTransferInfos(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                        String search){
        PageInfo<TransferInfoDTO> transferInfos = transferInfoService.findByTransferInfos(page, pageSize,search);
        return ResultVO.success(transferInfos);
    }

    /**
     * 分页查询发布店铺信息
     * */
    @GetMapping("/findAll")
    public ResultVO findAll(@RequestParam String search){
        List<TransferInfoDTO> transferInfoDTOS = transferInfoService.findAll(search);
        return ResultVO.success(transferInfoDTOS);
    }


    /**
     * 新增店铺信息
     * */
    @PostMapping
    public ResultVO createTransferInfo(@RequestBody TransferInfoReq tradeInfoReq){
        boolean createSuccess = transferInfoService.createTransferInfo(tradeInfoReq);
        return createSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRANSFER_CREATED_ERROR);
    }

    /**
     * 推广开关
     * */
    @GetMapping("/switchIsGeneralize")
    public ResultVO switchIsGeneralize(@RequestParam Integer id){
        boolean updateSuccess = transferInfoService.switchIsGeneralize(id);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRANSFER_UPDATE_ERROR);
    }

    /**
     * 批量删除店铺信息
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_DELETE_USER','ROLE_SUPER_ADMIN')")
    @DeleteMapping
    public ResultVO deletes(@RequestParam String ids){
        transferInfoService.deletes(ids);
        return ResultVO.success();
    }

    /**
     * 修改时单个删除店铺图片信息
     * */
    @DeleteMapping("/delete")
    public ResultVO delete(@RequestParam Long storeImgId){
        transferInfoService.delete(storeImgId);
        return ResultVO.success();
    }

    /**
     * 修改店铺信息
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PutMapping
    public ResultVO updateTransferInfo(@RequestBody TransferInfoReq tradeInfoReq){
        boolean updateSuccess = transferInfoService.updateTransferInfo(tradeInfoReq);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRANSFER_UPDATE_ERROR);
    }

    /**
     * 同步店铺信息到其它城市
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PostMapping("/synData")
    public ResultVO synTransferData(@RequestBody SynDataReq synDataReq){
        transferInfoService.synTransferData(synDataReq);
        return ResultVO.success();
    }


    /**
     * 修改置顶时间
     * */
    @PreAuthorize("hasAnyAuthority('SETTING_NORMAL_UPDATE_USER','ROLE_SUPER_ADMIN')")
    @PutMapping("/updateStickDate")
    public ResultVO updateStickDate(@RequestBody StickDay stickDay){
        boolean updateSuccess = transferInfoService.updateStickDate(stickDay);
        return updateSuccess ? ResultVO.success() : ResultVO.fail(ResponseEnum.TRANSFER_UPDATE_ERROR);
    }
}
