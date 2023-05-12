package com.api.cinema.repository;

import com.api.cinema.entity.Rap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RapRepository extends JpaRepository<Rap, Long> {
    @Query("Select u From Rap u WHERE u.cumRap.maCumRap = ?1")
    List<Rap> findByCumRapMaCumRap(int id);

    @Query("Select u From Rap u ")
    List<Rap> findALL();
}
