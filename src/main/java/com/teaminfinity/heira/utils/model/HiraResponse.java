package com.teaminfinity.heira.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiraResponse {
    private String url;
    private String origin;
    private Object body;
    private String status;
}
