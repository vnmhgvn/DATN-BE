package com.api.cinema.dto;



import lombok.Data;

import java.util.List;
@Data
public class HeThongRapDTO {
    private int maHeThongRap;
    
    private String tenHeThongRap;
    
    private String biDanh;
    
    private String logo;

    private List<CumRapDTO> cumRapChieu;
}
