package com.api.cinema.controller;

import com.api.cinema.entity.Ghe;
import com.api.cinema.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private GheService gheService;
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> UserPage() {

        return new ResponseEntity<String>("You have USER role.", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> defaultPage() {
        return new ResponseEntity<String>("You dont have role.", HttpStatus.OK);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> getAllBlogs(HttpServletRequest request) {
            return new ResponseEntity<String>("You have ADMIN role.", HttpStatus.OK);

    }


}
