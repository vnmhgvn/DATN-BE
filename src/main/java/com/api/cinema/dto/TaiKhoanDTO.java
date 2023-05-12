package com.api.cinema.dto;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class TaiKhoanDTO {
    private Long maTaiKhoan;
    private String hoTen;
    private String username;

    private String mail;

    private String sdt;

    private String matKhau;

    private Set<String> loaiTaiKhoanList;
}
