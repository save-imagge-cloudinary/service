package com.example.service.dto;

import lombok.Data;

/**
Created by NhanNguyen on 5/8/2021
@author: NhanNguyen
@date: 5/8/2021
*/
@Data
public class Response {
    private String message;
    private Object data;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
