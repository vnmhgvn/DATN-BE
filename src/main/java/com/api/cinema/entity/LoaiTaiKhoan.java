package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "loaiTaiKhoan")
public class LoaiTaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTaiKhoan;

    @NotEmpty
    private String tenLoaiTaiKhoan;
}
