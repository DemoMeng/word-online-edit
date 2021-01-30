package com.mqz.online.edit.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mqz
 * @since
 * @description wps密钥配置属性
 * @abount https://github.com/DemoMeng
 */
@Data
@Component
@ConfigurationProperties(prefix = "wps")
public class WpsProperties {

	private String domain;
	private String appid;
	private String appsecret;
	private String downloadHost;
	private String localDir;

}
