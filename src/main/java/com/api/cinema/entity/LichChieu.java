package com.api.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "lichChieu")
public class LichChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLichChieu;

    @NotEmpty
    private LocalDate ngayChieu;

    @NotEmpty
    private LocalTime gioChieu;

//    @NotEmpty
//    private int giaVe;

    @NotEmpty
    private int thoiLuong;

    @ManyToOne
    @JoinColumn(name = "phim_id")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "rap_id")
    private Rap rap;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "cumRap_id")
//    private CumRap cumRap;
//
////    @ManyToOne
////    @JsonIgnore
////    @JoinColumn(name = "heThongRap_id")
////    private HeThongRap heThongRap;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//   // @JsonIgnore
//    @JoinColumn(name = "heThongRap_id")
//    private HeThongRap heThongRap;
}
