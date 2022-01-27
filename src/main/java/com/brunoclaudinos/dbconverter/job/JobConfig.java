package com.brunoclaudinos.dbconverter.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job dataBaseConverterJob(
            @Qualifier("convertPlayerStep") Step convertPlayerStep
    ) {
        return jobBuilderFactory
                .get("db-converter-job")
                .start(convertPlayerStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
