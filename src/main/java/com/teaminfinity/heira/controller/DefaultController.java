package com.teaminfinity.heira.controller;

import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.utils.model.AInformation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class DefaultController {

    @Autowired
    private HiraMethods hira;

    @RequestMapping
    public ResponseEntity<?> handleInit(HttpServletRequest request){
        AInformation information = new AInformation(
          request.getRequestURI(),
          "Welcome to Kundu Api!",
          hira.BrowserSpecifyHeaders(request),
          HttpStatus.OK.value(),
          LocalDateTime.now()
        );
        return new ResponseEntity<>(information,HttpStatus.OK);
    }
}
