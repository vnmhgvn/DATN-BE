package com.api.cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/test")
public class TestController {
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
