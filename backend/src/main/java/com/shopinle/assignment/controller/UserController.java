package com.shopinle.assignment.controller;

import com.shopinle.assignment.dto.login.UserLoginRequestDto;
import com.shopinle.assignment.dto.login.UserLoginResponseDto;
import com.shopinle.assignment.dto.registration.UserRegistrationRequestDto;
import com.shopinle.assignment.dto.registration.UserRegistrationResponseDto;
import com.shopinle.assignment.model.UserProfile;
import com.shopinle.assignment.security.jwt.AuthenticationService;
import com.shopinle.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService auhenticationService;

    @RequestMapping(value="/login",method = RequestMethod.POST,produces = "application/json; charset=utf-8",
            consumes = "application/json; charset=utf-8")
    public UserLoginResponseDto login(@Validated @RequestBody UserLoginRequestDto userLoginRequestDto){
        UserLoginResponseDto response = userService.verifyUser(userLoginRequestDto);

        if (StringUtils.isEmpty(response.getError())){
            String token = auhenticationService.getAuthenticationToken(userLoginRequestDto);

            if (token != null){
                response.setToken(token);
            }
        }

        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8",
            consumes = "application/json; charset=utf-8")
    public UserRegistrationResponseDto register(@Validated @RequestBody UserRegistrationRequestDto request) {
        return userService.register(request);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public ResponseEntity<?> profile(HttpServletRequest request){
        UserProfile userProfile = auhenticationService.retrieveUser(request);
        return ResponseEntity.ok(userProfile);
    }
}
