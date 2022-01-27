package com.brunoclaudinos.dbconverter.step;

import com.brunoclaudinos.dbconverter.domain.old.model.PlayerOld;
import com.brunoclaudinos.dbconverter.domain.old.repository.PlayerOldRepository;
import com.brunoclaudinos.dbconverter.domain.target.model.PlayerTarget;
import com.brunoclaudinos.dbconverter.domain.target.repository.PlayerTargetRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Map;

@Configuration
public class ConvertPlayerStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final PlayerOldRepository playerOldRepository;
    private final PlayerTargetRepository playerTargetRepository;

    public ConvertPlayerStepConfig(StepBuilderFactory stepBuilderFactory, PlayerOldRepository playerOldRepository, PlayerTargetRepository playerTargetRepository) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.playerOldRepository = playerOldRepository;
        this.playerTargetRepository = playerTargetRepository;
    }

    @Bean(name = "convertPlayerStep")
    public Step convertPlayerStep() {
        return stepBuilderFactory
                .get("convertPlayerStep")
                .<PlayerOld, PlayerTarget>chunk(100)
                .reader(playerReader())
                .processor(new PlayerProcessor())
                .writer(playerWriter())
                .build();
    }

    public RepositoryItemReader<PlayerOld> playerReader() {
        return new RepositoryItemReaderBuilder<PlayerOld>()
                .repository(playerOldRepository)
                .pageSize(100)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("playerReader")
                .build();
    }

    private class PlayerProcessor implements ItemProcessor<PlayerOld, PlayerTarget> {
        @Override
        public PlayerTarget process(PlayerOld playerOld) throws Exception {
            PlayerTarget target = new PlayerTarget();
            target.setId(playerOld.getId());
            target.setName(playerOld.getDescription());
            target.setBirthdate(LocalDate.parse(playerOld.getBirthdate()));

            return target;
        }
    }

    public RepositoryItemWriter<PlayerTarget> playerWriter() {
        return new RepositoryItemWriterBuilder<PlayerTarget>()
                .repository(playerTargetRepository)
                .build();
    }
}
