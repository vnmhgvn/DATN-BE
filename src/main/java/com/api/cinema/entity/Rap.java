package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "rap")
public class Rap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCumRap;

    @NotEmpty
    private String tenRap;

    @OneToMany(mappedBy = "rap", cascade = CascadeType.ALL)
    private List<Ghe> gheList;

    @ManyToOne
    @JoinColumn(name = "cumRap_id")
    private CumRap cumRap;


}
