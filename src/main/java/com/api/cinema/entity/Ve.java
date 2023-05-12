package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ve")
public class Ve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maVe;

    @ManyToOne
    @JoinColumn(name = "lichChieu_id")
    private LichChieu lichChieu;

    @ManyToOne
    @JoinColumn(name = "ghe_id")
    private Ghe ghe;

    @NotEmpty
    private LocalDateTime dateTime;
}
