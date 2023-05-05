package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "taiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTaiKhoan;

    @NotEmpty
    private String hoTen;

    @NotEmpty
    @Email
    private String mail;

    @NotEmpty
    private String sdt;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matKhau;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
                joinColumns = {@JoinColumn(name = "maTaiKhoan")},
                inverseJoinColumns = {@JoinColumn(name = "maLoaiTaiKhoan")})
    private List<LoaiTaiKhoan> loaiTaiKhoanList;

}
