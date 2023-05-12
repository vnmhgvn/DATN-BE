package com.api.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tinTuc")
public class TinTuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTinTuc;

    @NotEmpty
    private String tacgia;

    @NotEmpty
    private String mota1;

    private String mota2;

    private String mota3;

    @NotEmpty
    private String image1;

    private String image2;

    private String image3;
    private LocalDateTime timeUpload;
    private int thich;
    private int chiaSe;
}
