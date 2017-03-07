package com.shopinle.assignment.service;

import com.shopinle.assignment.dto.login.UserLoginRequestDto;
import com.shopinle.assignment.dto.login.UserLoginResponseDto;
import com.shopinle.assignment.dto.registration.UserRegistrationRequestDto;
import com.shopinle.assignment.dto.registration.UserRegistrationResponseDto;

public interface UserService {
    UserLoginResponseDto verifyUser(UserLoginRequestDto userLoginRequestDto);
    UserRegistrationResponseDto register(UserRegistrationRequestDto request);
}
