package cc.lord;

import cc.lord.common.config.FebsProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cc.lord.*.dao")
@EnableConfigurationProperties({FebsProperties.class})
@EnableCaching
@EnableAsync
public class Application extends SpringBootServletInitializer {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("FEBS started up successfully at {} {}", LocalDate.now(), LocalTime.now());
    }
}
