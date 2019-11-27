package com.dpf.movies.repository;

import com.dpf.movies.domain.Performance;
import com.dpf.movies.domain.pk.PerformancePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, PerformancePK> {

}
