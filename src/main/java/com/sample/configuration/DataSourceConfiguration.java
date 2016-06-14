package com.sample.configuration;

import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public interface DataSourceConfiguration {
    DataSource dataSource();
}