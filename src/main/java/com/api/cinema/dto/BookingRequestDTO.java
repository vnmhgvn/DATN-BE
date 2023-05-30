package com.api.cinema.dto;

import lombok.Data;

import java.util.List;
@Data
public class BookingRequestDTO {
    private Long userId;
    private Integer scheduleId;
    private List<Integer> listSeatIds;
}
