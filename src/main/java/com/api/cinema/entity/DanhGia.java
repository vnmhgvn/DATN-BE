package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "danhGia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhGia;

    private String comment;

    private LocalDateTime ngayDanhGia;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "phim_id")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "taiKhoan_id")
    private TaiKhoan taiKhoan;
}
