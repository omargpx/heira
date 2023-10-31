package com.teaminfinity.heira.syntax.logic;

import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.utils.model.HiraResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class HMS implements HiraMethods {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ012345678987654321abcdefghijklmnñopqrstuvxyz";
    private static final SecureRandom sr = new SecureRandom();
    private final Random random  = SecureRandom.getInstanceStrong();

    public HMS() throws NoSuchAlgorithmException {
        // an exception is raised to keep the random number generation highly strong
    }

    @Override
    public String HiraId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int index = sr.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return "HiraId_"+getCurrentYear()+"-"+builder.toString();
    }

    @Override
    public HiraResponse getResponse(HttpServletRequest url, String origin, Object data, HttpStatus status) {
        return HiraResponse.builder()
          .url(url.getRequestURI())
          .origin(origin)
          .body(data)
          .status(status.name())
          .build();
    }

    @Override
    public Map<String, String> BrowserSpecifyHeaders(HttpServletRequest request) {
        Map<String, String> specificHeaders = new HashMap<>();
        specificHeaders.put("connection", request.getHeader("connection"));
        specificHeaders.put("browse-info", request.getHeader("sec-ch-ua"));
        specificHeaders.put("platform", request.getHeader("sec-ch-ua-platform"));
        specificHeaders.put("sys_info","/info");
        return specificHeaders;
    }

    private static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return Integer.toString(year).substring(2);
    }
}
