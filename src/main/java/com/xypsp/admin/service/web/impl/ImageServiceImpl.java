package com.xypsp.admin.service.web.impl;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.enums.ImageDirEnum;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.web.ImageService;
import com.xypsp.admin.utils.EnumUtil;
import com.xypsp.admin.utils.qiniu.ImageKit;
import com.xypsp.admin.utils.qiniu.ImageScalaKit;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rp
 */
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageScalaKit imageScalaKit;

    public ImageServiceImpl(ImageScalaKit imageScalaKit) {
        this.imageScalaKit = imageScalaKit;
    }

    /**
     * 上传图片
     * @param file  前端上传的文件
     * @param dir  上传到那个文件
     * @return ImageKit
     */
    @Override
    public ImageKit upload(MultipartFile file, Integer dir) {
        ImageDirEnum dirEnum = EnumUtil.getByCode(dir, ImageDirEnum.class);
        if (dirEnum == null) {
            throw new CustomizeException(ResponseEnum.IMAGE_ENUM_NOT_FOUND);
        }
        return imageScalaKit.upload(file, dirEnum.getMsg());
    }


    /**
     * 删除图片
     * @param key 图片地址或者图片的key
     */
    @Override
    public void delete(String key) {
        imageScalaKit.delete(key);
    }

    @Override
    public void deletes(String[] keys) {
        for (String key : keys) {
            imageScalaKit.delete(key);
        }
    }
}
