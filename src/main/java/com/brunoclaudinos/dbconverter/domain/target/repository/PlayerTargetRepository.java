package com.brunoclaudinos.dbconverter.domain.target.repository;

import com.brunoclaudinos.dbconverter.domain.target.model.PlayerTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTargetRepository extends JpaRepository<PlayerTarget, Long> {
}
