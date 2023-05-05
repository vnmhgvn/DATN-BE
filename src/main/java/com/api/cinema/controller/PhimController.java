package com.api.cinema.controller;

import com.api.cinema.dto.PhimDTO;
import com.api.cinema.dto.Response;
import com.api.cinema.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/QuanLyPhim", produces = "application/json")
public class PhimController {
    private static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";
    @Autowired
    private PhimService phimService;

    @GetMapping("/DanhSach")
    public ResponseEntity<List<PhimDTO>> danhSachPhim(){
        return new ResponseEntity<>(phimService.findAllMovie(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> chiTiet(@PathVariable(name = "id") Long id){
        Optional<PhimDTO> phimDTO = phimService.getById(id);
        if(phimDTO.isPresent()){
            return ResponseEntity.ok().body(new Response("ok","Tìm thành công",phimDTO.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("fail", "Không tìm thấy id = " +id,""));
        }

    }

    @PostMapping("/ThemMoiPhim")
    public ResponseEntity<Response> taoMoi(@RequestParam String tenPhim,
                                           @RequestParam String biDanh,
                                           @RequestParam String trailer,
                                           @RequestParam String moTa,
                                           @RequestParam LocalDate ngayKhoiChieu,
                                           @RequestParam int danhGia,
                                           @RequestParam MultipartFile hinhAnh) throws IOException {
        PhimDTO newPhimDto = new PhimDTO();
        String imageUUID;
        if(!hinhAnh.isEmpty()){
            imageUUID = hinhAnh.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, hinhAnh.getBytes());
        }else {
            imageUUID = "image";
        }
        newPhimDto.setHinhAnh(imageUUID);
        newPhimDto.setTenPhim(tenPhim);
        newPhimDto.setMoTa(moTa);
        newPhimDto.setTrailer(trailer);
        newPhimDto.setBiDanh(biDanh);
        newPhimDto.setNgayKhoiChieu(ngayKhoiChieu);
        newPhimDto.setDanhGia(danhGia);
        phimService.addMovie(newPhimDto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("ok","Thêm thành công", newPhimDto));
    }

    @PutMapping("/SuaPhim/{id}")
    public ResponseEntity<Response> suaPhim(@PathVariable(name = "id") Long id, @RequestBody PhimDTO phimDTO){
        Optional<PhimDTO> check = phimService.getById(id);
        if(check.isPresent()){
            phimDTO.setMaPhim(id);
            phimService.addMovie(phimDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK","Cập nhật thành công",phimDTO));
        }
        else{
            //phimService.addMovie(phimDTO);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Fail","Thất bại",""));
        }
    }

    @DeleteMapping("/Xoa/{id}")
    public ResponseEntity<Response> xoaPhim(@PathVariable(name = "id") Long id ){
        Optional<PhimDTO> check = phimService.getById(id);
        if(check.isPresent()){
            phimService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK","Xoá thành công",""));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Fail","Thất bại",""));
        }
    }
}
