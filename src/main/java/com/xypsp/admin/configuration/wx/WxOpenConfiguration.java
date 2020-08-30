package com.xypsp.admin.configuration.wx;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lj on 2019/12/22 17:07
 */
@Configuration
@EnableConfigurationProperties(WxOpenProperties.class)
@AllArgsConstructor
public class WxOpenConfiguration {

    private final WxOpenProperties wxOpenProperties;

    @Bean
    public WxOpenService wxOpenService() {
        WxOpenInMemoryConfigStorage wxConfigStorage = new WxOpenInMemoryConfigStorage();
        wxConfigStorage.setComponentAppId(wxOpenProperties.getAppId());
        wxConfigStorage.setComponentAppSecret(wxOpenProperties.getAppSecret());
        WxOpenService wxOpenService = new WxOpenServiceImpl();
        wxOpenService.setWxOpenConfigStorage(wxConfigStorage);
        return wxOpenService;
    }
}
