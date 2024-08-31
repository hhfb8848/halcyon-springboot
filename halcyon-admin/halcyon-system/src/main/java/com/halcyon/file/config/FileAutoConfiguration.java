package com.halcyon.file.config;

import com.halcyon.file.client.FileClientFactory;
import com.halcyon.file.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 22:07
 * @description: 文件配置
 */
@Configuration(proxyBeanMethods = false)
public class FileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }
}
