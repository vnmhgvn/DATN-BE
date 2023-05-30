package com.api.cinema.controller;

import com.api.cinema.dto.BookingRequestDTO;
import com.api.cinema.entity.Ve;
import com.api.cinema.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/QuanLyVe", produces = "application/json")
public class VeController {
    @Autowired
    private VeService veService;

    @PostMapping("/DatVe")
    public ResponseEntity<String> datVe(@RequestBody BookingRequestDTO bookingRequestDTO){
        try{
            veService.createNewBill(bookingRequestDTO);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn mua vé thành công!", HttpStatus.OK);
    }

    @GetMapping("/DanhSachVeTheoLichChieu/{id}")
    public ResponseEntity<List<Ve>> danhSachVeTheoLichChieu(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(veService.getVeByLichChieuId(id),HttpStatus.OK);
    }

    @GetMapping("/DanhSachVe")
    public ResponseEntity<List<Ve>> danhSachVe(){
        return new ResponseEntity<>(veService.GetAllVe(),HttpStatus.OK);
    }
}
