package com.api.cinema.repository;

import com.api.cinema.entity.Ghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe, Integer> {

    //@Query("SELECT g FROM Ghe g WHERE g.rap.maRap IN( SELECT l.rap.maRap FROM LichChieu l)")
    List<Ghe> findByRap_maRap(Integer rapId);
}
