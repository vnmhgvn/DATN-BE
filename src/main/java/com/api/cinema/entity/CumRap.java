package com.api.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "cumRap")
public class CumRap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCumRap;

    @NotEmpty
    private String tenCumRap;

    @NotEmpty
    private String diaChi;

    @OneToMany(mappedBy = "cumRap", cascade = CascadeType.ALL)
    private List<Rap> rapList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "heThongRap_id")
    private HeThongRap heThongRap;
}
