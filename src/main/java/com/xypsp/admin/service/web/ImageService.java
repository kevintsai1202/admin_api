package com.xypsp.admin.service.web;

import com.xypsp.admin.utils.qiniu.ImageKit;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rp
 */
public interface ImageService {
    /**
     * 上传图片
     * @param file
     * @param dir
     * @return
     */
    ImageKit upload(MultipartFile file, Integer dir);

    /**
     * 删除图片
     * @param key
     */
    void delete(String key);


    /**
     * 批量删除图片
     * @param keys
     */
    void deletes(String[] keys);


}
