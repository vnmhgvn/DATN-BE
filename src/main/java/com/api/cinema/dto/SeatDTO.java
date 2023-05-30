package com.api.cinema.dto;

import com.api.cinema.entity.Ghe;
import lombok.Data;

import java.util.List;

@Data
public class SeatDTO {
    private PhimDTO thongTinPhim;
    private List<Ghe> danhSachGhe;
}
