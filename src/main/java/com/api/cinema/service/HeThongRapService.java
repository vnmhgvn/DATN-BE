package com.api.cinema.service;

import com.api.cinema.entity.HeThongRap;
import com.api.cinema.repository.HeThongRapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeThongRapService {
    @Autowired
    private HeThongRapRepository heThongRapRepository;
    public List<HeThongRap> findAllHeThongRap(){
        return heThongRapRepository.findAll();
    }
}
