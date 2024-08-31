package com.halcyon;

import com.halcyon.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-4-9 22:2
 * @description: 启动类
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.halcyon.dao.mapper")
@RestController
@EnableAsync
public class HalcyonApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HalcyonApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(HalcyonApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "\n" +
                "  _    _          _      _______     ______  _   _ \n" +
                " | |  | |   /\\   | |    / ____\\ \\   / / __ \\| \\ | |\n" +
                " | |__| |  /  \\  | |   | |     \\ \\_/ / |  | |  \\| |\n" +
                " |  __  | / /\\ \\ | |   | |      \\   /| |  | | . ` |\n" +
                " | |  | |/ ____ \\| |___| |____   | | | |__| | |\\  |\n" +
                " |_|  |_/_/    \\_\\______\\_____|  |_|  \\____/|_| \\_|\n" +
                "                                                   \n" +
                "                                                   \n" +
                "应用启动成功! \n\t" +
                "Local: \t\thttp://localhost:" + port  + "\n\t" +
                "External: \thttp://" + ip + ":" + port  + "\n" +
                "----------------------------------------------------------");

    }

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("请通过前端地址访问。");
    }
}
