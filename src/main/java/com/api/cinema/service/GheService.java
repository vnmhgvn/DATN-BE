package com.api.cinema.service;

import com.api.cinema.dto.GheDTO;
import com.api.cinema.dto.PhimDTO;
import com.api.cinema.dto.SeatDTO;
import com.api.cinema.entity.Ghe;
import com.api.cinema.entity.LichChieu;
import com.api.cinema.entity.Rap;
import com.api.cinema.repository.GheRepository;
import com.api.cinema.repository.LichChieuRepository;
import com.api.cinema.repository.VeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GheService {
    @Autowired
    private GheRepository gheRepository;

    @Autowired
    private PhimService phimService;

    @Autowired
    private LichChieuRepository lichChieuRepository;

    @Autowired
    private VeRepository veRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Ghe> getGheByRapIdAndLichChieuId(Integer lichChieuId){
        Rap rap = lichChieuRepository.findById(lichChieuId).get().getRap();

        List<Ghe> gheList = gheRepository.findByRap_maRap(rap.getMaRap());

        List<Ghe> occupiedSeats  = veRepository.findByLichChieu_maLichChieu(lichChieuId)
                .stream().map(ve -> ve.getGhe())
                .collect(Collectors.toList());

        return occupiedSeats;

    }

    public SeatDTO getAllGheByLichChieu(int lichChieuId){
        Rap rap = lichChieuRepository.findById(lichChieuId).get().getRap();
        LichChieu lichChieu = lichChieuRepository.findById(lichChieuId).get();

        PhimDTO phimDTO = phimService.getById(lichChieu.getPhim().getMaPhim()).get();

        List<Ghe> gheList = gheRepository.findByRap_maRap(lichChieu.getRap().getMaRap());
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setDanhSachGhe(gheList);
        seatDTO.setThongTinPhim(phimDTO);
        return seatDTO;

    }
}
