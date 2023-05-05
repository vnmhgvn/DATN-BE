package com.api.cinema.repository;

import com.api.cinema.entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhimRepository extends JpaRepository<Phim, Long> {
}
