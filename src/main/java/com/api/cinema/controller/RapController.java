package com.api.cinema.controller;

import com.api.cinema.entity.CumRap;
import com.api.cinema.entity.HeThongRap;
import com.api.cinema.entity.Rap;
import com.api.cinema.service.CumRapService;
import com.api.cinema.service.HeThongRapService;
import com.api.cinema.service.RapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/QuanLyRap", produces = "application/json")
public class RapController {
    @Autowired
    private RapService rapService;

    @Autowired
    private CumRapService cumRapService;

    @Autowired
    private HeThongRapService heThongRapService;


    @GetMapping("/DanhSachRap/{id}")
    public ResponseEntity<List<Rap>> danhSachRap(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(rapService.findByMaCumRap(id), HttpStatus.OK);
    }

    @GetMapping("/DanhSachRapByHeThongRap/{id}")
    public ResponseEntity<List<Rap>> danhSachRapByHeThongRap(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(rapService.findByHeThongRap(id), HttpStatus.OK);
    }

    @GetMapping("/DanhSachCumRapByHeThongRap/{id}")
    public ResponseEntity<List<CumRap>> danhSachCumRapByHeThongRap(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(cumRapService.findByHeThongRap(id), HttpStatus.OK);
    }
    @GetMapping("/DanhSachRap")
    public ResponseEntity<List<Rap>> danhSachRap(){
        return new ResponseEntity<>(rapService.findAllRap(), HttpStatus.OK);
    }


    @GetMapping("/DanhSachHeThongRap")
    public ResponseEntity<List<HeThongRap>> danhSachHeThongRap(){
        return new ResponseEntity<>(heThongRapService.findAllHeThongRap(), HttpStatus.OK);
    }
}
