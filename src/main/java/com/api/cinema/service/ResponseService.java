package com.api.cinema.service;

import com.api.cinema.dto.*;
import com.api.cinema.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {
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
    @Autowired
    private HeThongRapRepository heThongRapRepository;

    @Autowired
    private CumRapRepository cumRapRepository;
    public ResponseDTO response(Long phimId){
        ResponseDTO responseDTO = new ResponseDTO();
        PhimDTO phimDTO = phimService.getById(phimId).get();

        //He thong rap
        List<HeThongRapDTO> heThongRapDTOList = heThongRapRepository.findAll()
                .stream()
                .map(heThongRap -> modelMapper.map(heThongRap, HeThongRapDTO.class))
                .collect(Collectors.toList());
        //Cum rap
        heThongRapDTOList.forEach(heThongRapDTO -> {
            List<CumRapDTO> cumRapDTOList = cumRapRepository.findByHeThongRap_maHeThongRap(heThongRapDTO.getMaHeThongRap())
                    .stream()
                    .map(cumRap -> modelMapper.map(cumRap,CumRapDTO.class))
                    .collect(Collectors.toList());

            // Lich chieu
            cumRapDTOList.forEach(cumRapDTO -> {
                List<LichChieuDTO> lichChieuDTOList = lichChieuRepository.findByPhim_maPhimAndRap_CumRap_maCumRap(phimId,cumRapDTO.getMaCumRap())
                        .stream()
                        .map(lichChieu -> modelMapper.map(lichChieu,LichChieuDTO.class))
                        .collect(Collectors.toList());
                cumRapDTO.setLichChieuPhim(lichChieuDTOList);
            });
            heThongRapDTO.setCumRapChieu(cumRapDTOList);
        });

        responseDTO.setPhim(phimDTO);
        responseDTO.setHeThongRapChieu(heThongRapDTOList);
        return  responseDTO;
        }
    }
