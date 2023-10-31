package com.teaminfinity.heira.syntax.service;

import com.teaminfinity.heira.utils.model.HiraResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface HiraMethods {
    String HiraId();
    HiraResponse getResponse(HttpServletRequest url, String origin, Object data, HttpStatus status);
    Map<String,String> BrowserSpecifyHeaders(HttpServletRequest request);
}
