package com.api.cinema.repository;

import com.api.cinema.entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhimRepository extends JpaRepository<Phim, Long> {

}
