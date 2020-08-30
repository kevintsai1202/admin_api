package com.xypsp.admin.configuration.qiniu;

import com.xypsp.admin.utils.qiniu.QiNiuUploadKit;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties(QiNiuProperties.class)
@org.springframework.context.annotation.Configuration
public class QiNiuConfiguration {

    private final QiNiuProperties properties;

    public QiNiuConfiguration(QiNiuProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public UploadManager uploadManager(Configuration configuration) {
        return new UploadManager(configuration);
    }

    @Bean
    public Configuration configuration() {
        return new Configuration(Region.huanan());
    }

    @Bean
    @ConditionalOnMissingBean
    public Auth auth() {
        return Auth.create(properties.getAccessKey(), properties.getSecretKey());
    }



    @Bean
    @ConditionalOnMissingBean
    public BucketManager bucketManager(Auth auth, Configuration configuration) {
        return new BucketManager(auth, configuration);
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationManager operationManager(Auth auth, Configuration configuration) {
        return new OperationManager(auth, configuration);
    }
    @Bean
    @ConditionalOnMissingBean
    public QiNiuUploadKit qiNiuUpload(Auth auth, UploadManager uploadManager, BucketManager bucketManager){
        QiNiuUploadKit upload = new QiNiuUploadKit();
        upload.setAuth(auth);
        upload.setBucket(properties.getBucket());
        upload.setUploadManager(uploadManager);
        upload.setBucketManager(bucketManager);
        return upload;
    }
}
