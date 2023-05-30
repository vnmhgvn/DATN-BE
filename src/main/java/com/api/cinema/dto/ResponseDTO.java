package com.api.cinema.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    public PhimDTO phim;
    public List<HeThongRapDTO> heThongRapChieu;
}
