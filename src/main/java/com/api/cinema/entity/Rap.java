package com.api.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int maRap;

    @NotEmpty
    private String tenRap;

    @OneToMany(mappedBy = "rap", cascade = CascadeType.ALL)
    private List<Ghe> gheList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cumRap_id")
    private CumRap cumRap;


}
