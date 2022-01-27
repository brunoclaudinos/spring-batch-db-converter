package com.brunoclaudinos.dbconverter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "oldEntityManagerFactory",
        transactionManagerRef = "oldTransactionManager",
        basePackages = "com.brunoclaudinos.dbconverter.domain.old.repository")
public class OldDataSourceConfig {

    @Bean(name = "oldDataSource")
    @ConfigurationProperties(prefix = "old.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oldEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oldEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("oldDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.brunoclaudinos.dbconverter.domain.old.model")
                .persistenceUnit("old")
                .build();
    }

    @Bean(name = "oldTransactionManager")
    public PlatformTransactionManager oldTransactionManager(
            @Qualifier("oldEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
