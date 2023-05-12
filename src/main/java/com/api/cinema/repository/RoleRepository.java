package com.api.cinema.repository;

import com.api.cinema.entity.ERole;
import com.api.cinema.entity.LoaiTaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<LoaiTaiKhoan, Integer> {
    Optional<LoaiTaiKhoan> findByName(ERole name);
}
