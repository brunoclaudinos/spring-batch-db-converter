package com.brunoclaudinos.dbconverter.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig extends DefaultBatchConfigurer {

    @Qualifier("targetTransactionManager")
    private final PlatformTransactionManager targetTransactionManager;

    public BatchConfig(PlatformTransactionManager targetTransactionManager) {
        this.targetTransactionManager = targetTransactionManager;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return targetTransactionManager;
    }

    @Override
    public void setDataSource(@Qualifier("targetDataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

}
