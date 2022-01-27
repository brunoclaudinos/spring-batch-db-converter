package com.brunoclaudinos.dbconverter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "targetEntityManagerFactory",
        transactionManagerRef = "targetTransactionManager",
        basePackages = "com.brunoclaudinos.dbconverter.domain.target.repository")
public class TargetDataSourceConfig {

    @Primary
    @Bean(name = "targetDataSource")
    @ConfigurationProperties(prefix = "target.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "targetEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean targetEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("targetDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.brunoclaudinos.dbconverter.domain.target.model")
                .persistenceUnit("target")
                .build();
    }

    @Primary
    @Bean(name = "targetTransactionManager")
    public PlatformTransactionManager targetTransactionManager(
            @Qualifier("targetEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
