package com.api.cinema.repository;

import com.api.cinema.entity.CumRap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CumRapRepository extends JpaRepository<CumRap, Integer> {
    List<CumRap> findByHeThongRap_maHeThongRap(int heThongRapId);
}
