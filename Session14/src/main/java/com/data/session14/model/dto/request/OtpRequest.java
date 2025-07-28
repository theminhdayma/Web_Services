package com.data.session14.model.dto.request;

import lombok.Data;

@Data
public class OtpRequest {
    private String username;
    private String password;
    private String otp;
}
