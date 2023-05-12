package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "taiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTaiKhoan;
    @NotEmpty
    private String username;
    @NotEmpty
    private String hoTen;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String sdt;

    @NotEmpty
    private String matKhau;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
                joinColumns = {@JoinColumn(name = "maTaiKhoan")},
                inverseJoinColumns = {@JoinColumn(name = "maLoaiTaiKhoan")})
    private Set<LoaiTaiKhoan> loaiTaiKhoanList;


    public TaiKhoan() {
    }

    public TaiKhoan(String taiKhoan, String hoTen, String mail, String sdt, String matKhau) {
        this.username = taiKhoan;
        this.hoTen = hoTen;
        this.email = mail;
        this.sdt = sdt;
        this.matKhau = matKhau;
    }
}
