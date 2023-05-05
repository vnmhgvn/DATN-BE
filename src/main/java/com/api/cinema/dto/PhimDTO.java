package com.api.cinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PhimDTO {
    private Long maPhim;
    
    private String tenPhim;
    
    private String biDanh;
    
    private String trailer;
    
    private String hinhAnh;
    
    private String moTa;
    
    private LocalDate ngayKhoiChieu;

    private int danhGia;
}
