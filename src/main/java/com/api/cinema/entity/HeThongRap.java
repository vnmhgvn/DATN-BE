package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "heThongRap")
public class HeThongRap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHeThongRap;
    @NotEmpty
    private String tenHeThongRap;
    @NotEmpty
    private String biDanh;
    @NotEmpty
    private String logo;

    @OneToMany(mappedBy = "heThongRap", cascade = CascadeType.ALL)
    private List<CumRap> cumRapList;

}
