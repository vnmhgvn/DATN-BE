package com.api.cinema.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "ghe")
public class Ghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maGhe;

    @NotEmpty
    private String tenGhe;

    @NotEmpty
    private String loaiGhe;

    @NotEmpty
    private String stt;

    @NotEmpty
    private double giaVe;

    @NotEmpty
    private boolean daDat;

    @ManyToOne
    @JoinColumn(name = "rap_id")
    private Rap rap;
    @ManyToOne
    @JoinColumn(name = "taiKhoan_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaiKhoan taiKhoan;
}
