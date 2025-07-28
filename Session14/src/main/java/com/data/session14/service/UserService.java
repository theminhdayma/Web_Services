package com.data.session14.service;


import com.data.session14.model.dto.request.OtpRequest;
import com.data.session14.model.dto.request.UserLogin;
import com.data.session14.model.dto.request.UserRegister;
import com.data.session14.model.dto.response.JWTResponse;
import com.data.session14.model.entity.User;

public interface UserService {
    User registerUser(UserRegister ur);
    JWTResponse loginUser(UserLogin ul);
    User verifyPassword(String username, String password);
    String generateOtp();
    Object verifyOtpAndLogin(OtpRequest request);
    void logoutAllSessions();
    void save(User user);
}
