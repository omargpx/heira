package com.teaminfinity.heira.controller;

import com.teaminfinity.heira.entity.User;
import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.syntax.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private HiraMethods HIRA;
    @Autowired
    private UserService service;
    private final String svr_name = "USER_SERVICE";

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "username",required = false)String username,
                                    @RequestParam(name = "hiraId",required = false)String hiraId,
                                    HttpServletRequest request){
        if(null!=username)
            return ResponseEntity.ok(HIRA.getResponse(request,svr_name,service.filter(username),HttpStatus.OK));
        if(null!=hiraId)
            return ResponseEntity.ok(HIRA.getResponse(request,svr_name,service.filter(hiraId),HttpStatus.OK));
        return ResponseEntity.ok(HIRA.getResponse(request,svr_name,service.getAll(),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user,
                                  HttpServletRequest request){
        return ResponseEntity.ok(HIRA.getResponse(request,svr_name,service.save(user), HttpStatus.OK));
    }
}
