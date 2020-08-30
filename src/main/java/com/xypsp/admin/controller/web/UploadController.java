package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.response.ResultVO;
import com.xypsp.admin.service.web.ImageService;
import com.xypsp.admin.utils.qiniu.ImageKit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rp
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private final ImageService imageService;

    public UploadController(ImageService imageService) {
        this.imageService = imageService;
    }


    /**
     * 上传图片
     * */
    @PostMapping
    public ResultVO upload(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type) {
        ImageKit image = imageService.upload(file, type);
        return ResultVO.success(image);
    }

    /**
     * 删除图片
     * */
    @DeleteMapping
    public ResultVO delete(@RequestParam("key") String key) {
        imageService.delete(key);
        return ResultVO.success();
    }

    /**
     * 批量删除图片
     * */
    @DeleteMapping("/deletes")
    public ResultVO deletes(@RequestParam("keys") String[] keys){
        imageService.deletes(keys);
        return ResultVO.success();
    }
}
