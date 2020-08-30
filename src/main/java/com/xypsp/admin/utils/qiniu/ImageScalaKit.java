package com.xypsp.admin.utils.qiniu;


import com.xypsp.admin.configuration.qiniu.QiNiuProperties;
import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.enums.ResponseEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


/**
 * @author rp
 */
@Component
public class ImageScalaKit {

    private final QiNiuUploadKit qiNiuUploadKit;
    private final QiNiuProperties qiNiuProperties;

    public ImageScalaKit(QiNiuUploadKit qiNiuUploadKit, QiNiuProperties qiNiuProperties) {
        this.qiNiuUploadKit = qiNiuUploadKit;
        this.qiNiuProperties = qiNiuProperties;
    }

    public ImageKit upload(MultipartFile file, String prefix) {
        ImageKit imageKit;
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String extension = fileExtension(file);
            DefaultPutRet ret = qiNiuUploadKit.upload(file.getBytes(), prefix + "/" + uuid + extension);
            imageKit = initResult(true, ret);
            return imageKit;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomizeException(ResponseEnum.IMAGE_UPLOAD_FAIL);
        }

    }


    /**删除*/
    public boolean delete(String imgUrl) {
        String key = ImageKit.getKey(imgUrl, qiNiuProperties.getDomain());
        try {
            qiNiuUploadKit.delete(key);
            return true;
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ImageKit initResult(boolean isSuccess, DefaultPutRet ret) {
        ImageKit imageKit = new ImageKit();
        imageKit.setHash(ret.hash);
        imageKit.setKey(ret.key);
        imageKit.setUrl(qiNiuProperties.getDomain() + ret.key);
        return imageKit;
    }

    /**获得文件后缀名*/
    private String fileExtension(MultipartFile file) {
        int begin = file.getOriginalFilename().indexOf(".");
        if (begin == -1 || file.getOriginalFilename().length() == 0) {
            return "";
        }
        return file.getOriginalFilename().substring(begin);
    }

}
