package com.api.cinema.controller;

import com.api.cinema.dto.*;
import com.api.cinema.entity.Ghe;
import com.api.cinema.entity.LichChieu;
import com.api.cinema.service.GheService;
import com.api.cinema.service.LichChieuService;
import com.api.cinema.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/QuanLyLichChieu", produces = "application/json")
public class LichChieuController {
    @Autowired
    private LichChieuService lichChieuService;

    @Autowired
    private GheService gheService;

    @Autowired
    private ResponseService responseService;



    @GetMapping("/GetAll/{id}")
    public ResponseEntity<ResponseDTO> getAll(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(responseService.response(id),HttpStatus.OK);
    }


    @GetMapping("/DanhSachLichChieu")
    public ResponseEntity<List<LichChieuDTO>> danhSachLichChieu(){
        return new ResponseEntity<>(lichChieuService.findAllLichChieu(), HttpStatus.OK);
    }

    @GetMapping("/LichChieuTheoPhim/{id}")
    public  ResponseEntity<Response> lichChieuTheoPhim(@PathVariable(name = "id") Long id){
        List<LichChieu> lichChieu = lichChieuService.getThongTinLichChieuByMaPhim(id);
        return ResponseEntity.ok().body(new Response("ok","Thành công",lichChieu));
    }

    @GetMapping("/LayGheTheoLichChieu/{id}")
    public  SeatDTO gheTheoLichChieu(@PathVariable(name = "id") int id){
        return gheService.getAllGheByLichChieu(id);
    }



//    @GetMapping("/LichChieuTheoPhimVaRap")
//    public  ResponseEntity<Response> lichChieuTheoPhimVaRap(@PathVariable(name = "phimId") Long phimId, @PathVariable(name = "rapId")int rapId){
//        List<LichChieu> lichChieu = lichChieuService.getThongTinLichChieuByMaPhimVaMaRap(phimId,rapId);
//        return ResponseEntity.ok().body(new Response("ok","Thành công",lichChieu));
//    }

    @GetMapping("/ChiTietLichChieu/{id}")
    public ResponseEntity<Response> chiTiet(@PathVariable(name = "id") int id){
        //Optional<LichChieuDTO> lichChieuDTO = lichChieuService.getById(id);
        LichChieu lichChieu = lichChieuService.getById(id);
//        if(lichChieu != Null){
        return ResponseEntity.ok().body(new Response("ok","Tìm thành công",lichChieu));
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("fail", "Không tìm thấy id = " +id,""));
//        }

    }





    @PostMapping("/ThemMoiLichChieu")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Response> taoMoi(

                                           @RequestParam int maRap,
                                           @RequestParam Long maPhim,
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayChieu,
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime gioChieu
                                           ){
        LichChieu newLichChieu = new LichChieu();
        newLichChieu.setNgayChieu(ngayChieu);
        newLichChieu.setGioChieu(gioChieu);
//        newLichChieu.setGiaVe(giaVe);
        newLichChieu.setThoiLuong(120);
        lichChieuService.addLichChieu(newLichChieu,maPhim,maRap);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("ok","Thêm thành công", newLichChieu));
    }



    @GetMapping("/GetGheByLichChieu/{id}")
    public List<Ghe> getAll(@PathVariable int id) {
        return gheService.getGheByRapIdAndLichChieuId(id);
    }
}
