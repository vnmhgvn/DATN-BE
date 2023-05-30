package com.api.cinema.service;

import com.api.cinema.dto.*;
import com.api.cinema.entity.LichChieu;
import com.api.cinema.entity.Phim;
import com.api.cinema.entity.Rap;
import com.api.cinema.repository.LichChieuRepository;
import com.api.cinema.repository.PhimRepository;
import com.api.cinema.repository.RapRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LichChieuService {
    @Autowired
    private LichChieuRepository lichChieuRepository;

    @Autowired
    private PhimRepository phimRepository;

    @Autowired
    private PhimService phimService;
    @Autowired
    private RapRepository rapRepository;

    @Autowired
    private ModelMapper modelMapper;
    public List<LichChieuDTO> findAllLichChieu()
    {
        return lichChieuRepository.findAll()
                .stream()
                .map(lichChieu -> modelMapper.map(lichChieu,LichChieuDTO.class))
                .collect(Collectors.toList());
    }


    public LichChieu getById(int id){
        return lichChieuRepository.findById(id).get();
    }

    public void addLichChieu(LichChieu lichChieu, Long phimID,int rapID){

        Phim phim = phimRepository.findById(phimID).get();
        Rap rap = rapRepository.findById(rapID).get();
        lichChieu.setRap(rap);
        lichChieu.setPhim(phim);
        lichChieuRepository.save(lichChieu);
    }

    public void deleteLichChieu(int id){
        lichChieuRepository.deleteById(id);
    }

    public List<LichChieu> getThongTinLichChieuByMaPhim(Long phimId){
        return lichChieuRepository.findByPhim_maPhim(phimId);
    }

    public List<LichChieu> getThongTinLichChieuByMaPhimVaMaRap(Long phimId, int rapId){
        return lichChieuRepository.findByPhim_maPhimAndRap_maRap(phimId,rapId);
    }

    public List<LichChieu> getThongTinLichChieuByMaPhimVaCumRap(Long phimId, int cumRapId){
        return lichChieuRepository.findByPhim_maPhimAndRap_CumRap_maCumRap(phimId,cumRapId);
    }
}
