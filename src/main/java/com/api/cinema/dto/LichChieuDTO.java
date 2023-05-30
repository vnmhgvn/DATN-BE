package com.api.cinema.dto;

import com.api.cinema.entity.Phim;
import com.api.cinema.entity.Rap;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LichChieuDTO {
    private int maLichChieu;
    private Long giaVe;
    private LocalTime gioChieu;
    private LocalDate ngayChieu;
    private int thoiLuong;
    private String tenPhim;
    private String maRap;
    private String tenRap;


}
