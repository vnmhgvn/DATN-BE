package com.api.cinema.dto;


import com.api.cinema.entity.Rap;
import lombok.Data;


import java.util.List;
@Data
public class CumRapDTO {
    private int maCumRap;

    private String tenCumRap;

    private String diaChi;

    private List<Rap> rapList;

    private HeThongRapDTO heThongRap;
}
