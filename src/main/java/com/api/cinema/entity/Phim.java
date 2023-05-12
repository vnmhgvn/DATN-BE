package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhim;
    @NotEmpty
    private String tenPhim;
    @NotEmpty
    private String biDanh;
    @NotEmpty
    private String trailer;
    @NotEmpty
    private String hinhAnh;
    @NotEmpty
    @Size(max = 60000)
    private String moTa;
    @NotEmpty
    private LocalDate ngayKhoiChieu;

    private int danhGia;
}
