package com.api.cinema.service;

import com.api.cinema.dto.BookingRequestDTO;
import com.api.cinema.entity.Ghe;
import com.api.cinema.entity.LichChieu;
import com.api.cinema.entity.TaiKhoan;
import com.api.cinema.entity.Ve;
import com.api.cinema.repository.GheRepository;
import com.api.cinema.repository.LichChieuRepository;
import com.api.cinema.repository.UserRepository;
import com.api.cinema.repository.VeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeService {
    @Autowired
    private VeRepository veRepository;

    @Autowired
    private GheRepository gheRepository;

    @Autowired
    private LichChieuRepository lichChieuRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Ve> getVeByUserId(Long userId){
        return veRepository.findByTaiKhoan_maTaiKhoan(userId);
    }

    public List<Ve> GetAllVe(){
        return veRepository.findAll();
    }

    public List<Ve> getVeByLichChieuId(int lichChieuId){
        return veRepository.findByLichChieu_maLichChieu(lichChieuId);
    }

    public void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException{
        LichChieu lichChieu = lichChieuRepository.findById(bookingRequestDTO.getScheduleId()).get();

        TaiKhoan user = userRepository.findById(bookingRequestDTO.getUserId()).get();

        bookingRequestDTO.getListSeatIds().forEach(seatId ->{
            if (!veRepository.findByLichChieu_maLichChieuAndGhe_maGhe(lichChieu.getMaLichChieu(), seatId)
                    .isEmpty()) {
                throw new RuntimeException("Đã có người đặt");
            }
            Ghe ghe = gheRepository.getById(seatId);
            ghe.setDaDat(true);
            Ve ve = new Ve();
            ve.setDateTime(LocalDateTime.now());
            ve.setTaiKhoan(user);
            ve.setLichChieu(lichChieu);
            ve.setGhe(ghe);
            veRepository.save(ve);
        });


    }

}
