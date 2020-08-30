package com.xypsp.admin.configuration.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lj on 2019/12/19 22:17
 */
@Data
@ConfigurationProperties(prefix = "wx.op")
public class WxOpenProperties {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    /**
     * 设置微信公众号密匙
     */
    private String appSecret;


}
