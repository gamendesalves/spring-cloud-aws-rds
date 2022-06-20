package br.com.rds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.jdbc.config.annotation.RdsInstanceConfigurer;
import io.awspring.cloud.jdbc.datasource.TomcatJdbcDataSourceFactory;

@Configuration
public class RdsConfiguration {

  @Bean
  public RdsInstanceConfigurer rdsInstanceConfigurer() {
    return () -> {
      var dataSourceFactory = new TomcatJdbcDataSourceFactory();
      dataSourceFactory.setInitialSize(20);
      dataSourceFactory.setValidationQuery("SELECT 1 FROM DUAL");
      return dataSourceFactory;
    };
  }

}
