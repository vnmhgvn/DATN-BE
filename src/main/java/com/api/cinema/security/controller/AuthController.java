package com.api.cinema.security.controller;

import com.api.cinema.dto.Response;
import com.api.cinema.dto.TaiKhoanDTO;
import com.api.cinema.entity.ERole;
import com.api.cinema.entity.LoaiTaiKhoan;
import com.api.cinema.entity.TaiKhoan;
import com.api.cinema.repository.RoleRepository;
import com.api.cinema.repository.UserRepository;
import com.api.cinema.security.jwt.JwtResponse;
import com.api.cinema.security.jwt.JwtUtils;
import com.api.cinema.security.service.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody TaiKhoanDTO user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getMatKhau()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody TaiKhoanDTO user){
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new Response("error","Đã tồn tại người dùng, vui lòng dùng tên đăng nhập khác", ""));
        }
        if(userRepository.existsByEmail(user.getMail())){
            return ResponseEntity.badRequest().body(new Response("error","Đã tồn tại email, vui lòng dùng email khác", ""));
        }

        TaiKhoan newUser = new TaiKhoan(user.getUsername(), user.getHoTen(), user.getMail(), user.getSdt(), encoder.encode(user.getMatKhau()));

        Set<String> strRoles  = user.getLoaiTaiKhoanList();
        Set<LoaiTaiKhoan> roles = new HashSet<>();

        if (strRoles == null) {
            LoaiTaiKhoan userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        LoaiTaiKhoan adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        LoaiTaiKhoan modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        LoaiTaiKhoan userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

//        if(roleList == null){
//            LoaiTaiKhoan userRole = roleRepository.findByName("USER")
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }else{
//            roleList.forEach(role -> {
//                switch (role) {
//                    case "ADMIN":
//                        LoaiTaiKhoan adminRole = roleRepository.findByName("ADMIN")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "MOD":
//                        LoaiTaiKhoan modRole = roleRepository.findByName("MOD")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        LoaiTaiKhoan userRole = roleRepository.findByName("USER")
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }

        newUser.setLoaiTaiKhoanList(roles);
        userRepository.save(newUser);
        return ResponseEntity.ok(new Response("success","Đăng ký thành công",""));
    }
}
