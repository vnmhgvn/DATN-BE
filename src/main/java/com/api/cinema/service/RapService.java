package com.api.cinema.service;
import com.api.cinema.dto.RapDTO;
import com.api.cinema.entity.Rap;
import com.api.cinema.repository.RapRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RapService {
    @Autowired
    private RapRepository rapRepository;

    public List<Rap> findAllRap(){
        return rapRepository.findAll();
    }

    public Optional<Rap> getRapById(Long id){
        return rapRepository.findById(id);

    }

    public List<Rap> findByMaCumRap(int id){
        return rapRepository.findByCumRapMaCumRap(id);
    }
}
