package com.shopinle.assignment.controller;

import com.shopinle.assignment.dto.login.UserLoginRequestDto;
import com.shopinle.assignment.dto.login.UserLoginResponseDto;
import com.shopinle.assignment.dto.registration.UserRegistrationRequestDto;
import com.shopinle.assignment.dto.registration.UserRegistrationResponseDto;
import com.shopinle.assignment.model.UserProfile;
import com.shopinle.assignment.security.jwt.AuthenticationService;
import com.shopinle.assignment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private MockHttpServletRequest request;

    @Test
    public void shouldLogin() {
        UserLoginResponseDto userLoginResponse = new UserLoginResponseDto();
        userLoginResponse.setStatus("ok");

        UserLoginRequestDto userLoginRequest = new UserLoginRequestDto();
        userLoginRequest.setUsername("kamil");
        userLoginRequest.setPassword("Aa123456");



        when(userService.verifyUser(eq(userLoginRequest))).thenReturn(userLoginResponse);

        UserLoginResponseDto loginResponse = userController.login(userLoginRequest);

        assertThat(loginResponse.getStatus(), equalTo("ok"));
    }

    @Test
    public void shouldRegister() {
        UserRegistrationResponseDto response = new UserRegistrationResponseDto();
        response.setStatus("ok");

        UserRegistrationRequestDto request = new UserRegistrationRequestDto();
        request.setUsername("oso");
        request.setEmail("abc@gmail.com");
        request.setPassword("Aa123456");

        when(userService.register(eq(request))).thenReturn(response);

        UserRegistrationResponseDto loginResponse = userController.register(request);

        assertThat(loginResponse.getStatus(),equalTo("ok"));
    }

    @Test
    public void profile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("kamil");

        when(authenticationService.retrieveUser(eq(request))).thenReturn(userProfile);

        ResponseEntity<UserProfile> profileResponse = (ResponseEntity<UserProfile>) userController.profile(request);

        assertThat(profileResponse.getBody().getUsername(),equalTo("kamil"));
    }

}