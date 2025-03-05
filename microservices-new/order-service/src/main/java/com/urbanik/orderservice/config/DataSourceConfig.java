//package com.urbanik.orderservice.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
///**
// * @author kurbanik
// */
//
//@Configuration
//public class DataSourceConfig {
//
////    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(env.getProperty());
//        config.setUsername(env.getProperty());
//        config.setPassword(env.getProperty());
//        config.setMinimumIdle(1);
//        config.setMaximumPoolSize(5);
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setIdleTimeout(600000);
//        config.setMaxLifetime(1800000);
//        config.setConnectionTimeout(30000);
//        return new HikariDataSource(config);
//    }
//}
