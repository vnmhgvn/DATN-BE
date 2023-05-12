package com.api.cinema.service;

import com.api.cinema.dto.PhimDTO;
import com.api.cinema.entity.Phim;
import com.api.cinema.repository.PhimRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhimService {
    @Autowired
    private PhimRepository phimRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PhimDTO> findAllMovie(){
        return phimRepository.findAll()
                .stream()
                .map(phim -> modelMapper.map(phim, PhimDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<PhimDTO> getById(Long id){
        Optional<Phim> phim = phimRepository.findById(id);


        return Optional.ofNullable(modelMapper.map(phim.get(), PhimDTO.class));
    }

    public void addMovie(PhimDTO phimDTO){
        Phim phimReq = modelMapper.map(phimDTO, Phim.class);
        phimRepository.save(phimReq);
    }

    public void deleteMovie(Long id){
        phimRepository.deleteById(id);
    }

}
