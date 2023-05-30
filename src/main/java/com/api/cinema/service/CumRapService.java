package com.api.cinema.service;

import com.api.cinema.entity.CumRap;
import com.api.cinema.repository.CumRapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CumRapService {
    @Autowired
    private CumRapRepository cumRapRepository;

    public List<CumRap> findByHeThongRap(int id){
        return cumRapRepository.findByHeThongRap_maHeThongRap(id);
    }
}
