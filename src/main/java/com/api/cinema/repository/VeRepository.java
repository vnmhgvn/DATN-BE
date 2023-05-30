package com.api.cinema.repository;

import com.api.cinema.entity.Ve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VeRepository extends JpaRepository<Ve,Integer> {
    //@Query("SELECT v FROM Ve v WHERE v.lichChieu.maLichChieu =?1")
    List<Ve> findByLichChieu_maLichChieu(Integer lichChieuId);
    //@Query("SELECT v FROM Ve v WHERE v.lichChieu.maLichChieu =?1 AND v.ghe.maGhe = ?2")
    List<Ve> findByLichChieu_maLichChieuAndGhe_maGhe(Integer lichChieuId, Integer gheId);

    //@Query("SELECT v FROM Ve v WHERE v.ghe.maGhe IN (SELECT g.maGhe FROM Ghe g WHERE g.taiKhoan.maTaiKhoan=:userId) ORDER BY v.maVe DESC")
    List<Ve> findByTaiKhoan_maTaiKhoan(Long userId);
}
