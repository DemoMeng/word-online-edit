package com.mqz.online.edit.config.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


/**
 * @author mqz
 * @since 
 * @description
 * @abount https://github.com/DemoMeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
@ConfigurationProperties(prefix = "server")
@Primary
public class ServerProperties extends org.springframework.boot.autoconfigure.web.ServerProperties {

    private String domain;

}
