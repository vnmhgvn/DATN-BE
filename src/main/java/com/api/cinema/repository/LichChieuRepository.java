package com.api.cinema.repository;

import com.api.cinema.dto.LichChieuDTO;
import com.api.cinema.entity.LichChieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LichChieuRepository extends JpaRepository<LichChieu,Integer> {
//    @Query(value = "SELECT l.*,  r.*,cr.*, htr.* FROM LichChieu l , Rap r, CumRap cr , HeThongRap htr" +
//            "WHERE l.rap.maRap = r.maRap  " +
//            "AND cr.maCumRap = r.cumRap.maCumRap   " +
//            "AND htr.maHeThongRap = cr.heThongRap.maHeThongRap " +
//            "AND l.phim.maPhim = ?1",
//            nativeQuery = true)
    List<LichChieu> findByPhim_maPhim(Long phimId);

    List<LichChieu> findByPhim_maPhimAndRap_maRap(Long phimId,int rapId);

    List<LichChieu> findByRap_CumRap_maCumRap(int cumRapId);

    List<LichChieu> findByPhim_maPhimAndRap_CumRap_maCumRap(Long phimId, int cumRapId);
//    @Query(value = "SELECT new com.api.cinema.dto.LichChieuDTO" +
//            "(lc.maLichChieu , lc.giaVe, lc.gioChieu, lc.ngayChieu" +
//            ",lc.thoiLuong,p.tenPhim,p.hinhAnh,r.maRap,r.tenRap,cr.maCumRap" +
//            ",cr.tenCumRap,cr.diaChi,htr.logo,htr.tenHeThongRap ) " +
//            "FROM LichChieu lc " +
//            "JOIN lc.rap r " +
//            "JOIN lc.phim p " +
//            "JOIN r.cumRap cr " +
//            "JOIN cr.heThongRap htr " +
//            "WHERE lc.maLichChieu = :lichChieuId")
    //List<LichChieuDTO> getLichChieuById( Integer lichChieuId);

//    @Query("SELECT lc.Phi.maPhim,p.tenPhim,p.hinhAnh FROM LichChieu lc" +
//            "JOIN LichChieu lc ON lc.Phim.phimId = p.maPhim" +
//            "JOIN Rap r ON lc.Rap.rapId = r.maRap" +
//            "WHERE r.cumRapId = :cumRapId ")
//    Object[] getPhimByCumRap(@Param("cumRapId") int cumRapId);
}
