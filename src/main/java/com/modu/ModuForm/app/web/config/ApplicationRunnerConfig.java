package com.modu.ModuForm.app.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

@Slf4j
@Configuration
public class ApplicationRunnerConfig {

    private static final String HOST_NAME = "localhost:8080";
    private static final String LOG_FORMAT =
             "Loading Database With InMemory Database \n" +
                "Database Name: {} \n" +
                "Connect Database with this url: {} \n"+
                "URL for access to Database: {} \n"
            ;

    @Bean
    @Profile("default")
    ApplicationRunner applicationRunner(DataSource dataSource, H2ConsoleProperties h2ConsoleProperties) {
        return args -> {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

            log.info(LOG_FORMAT,
                    metaData.getDatabaseProductName(),
                    metaData.getURL(),
                    HOST_NAME + h2ConsoleProperties.getPath());
        };
    }
}
