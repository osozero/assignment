package com.shopinle.assignment.service;


import com.shopinle.assignment.dto.login.UserLoginRequestDto;
import com.shopinle.assignment.dto.login.UserLoginResponseDto;
import com.shopinle.assignment.dto.registration.UserRegistrationRequestDto;
import com.shopinle.assignment.dto.registration.UserRegistrationResponseDto;
import com.shopinle.assignment.enums.Exceptions;
import com.shopinle.assignment.model.User;
import com.shopinle.assignment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserLoginResponseDto verifyUser(UserLoginRequestDto userLoginRequestDto) {

        User user = getUser(userLoginRequestDto);

        if (user == null) {
            UserLoginResponseDto nonExistingUser = new UserLoginResponseDto();
            nonExistingUser.setStatus("fail");
            nonExistingUser.setError("Hatalı giriş yaptınız. Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz.");

            return nonExistingUser;
        }
        if (bCryptPasswordEncoder.matches(userLoginRequestDto.getPassword(),user.getPassword())) {
            return UserLoginResponseDto.buildFrom(user);
        } else {
            return buildForError(Exceptions.WRONG_PASSWORD);
        }

    }

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request) {
        UserRegistrationResponseDto response = new UserRegistrationResponseDto();

        User user = userRepository.findByEmail(request.getEmail());

        if (!StringUtils.isEmpty(user)){
            response.setStatus("fail");
            response.setError("User has been already created");
            return response;
        }

        return createNewUser(request,response);
    }

    private UserRegistrationResponseDto createNewUser(UserRegistrationRequestDto request, UserRegistrationResponseDto response){
        try {
            User user = this.createAndSavePendingUser(request);

            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setStatus("ok");
            response.setUserId(user.getId());
            return response;
        } catch (Exception e) {

            e.printStackTrace();
            response.setStatus("fail");
            return response;
        }
    }

    private User createAndSavePendingUser(UserRegistrationRequestDto request){

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return user;
    }

    private UserLoginResponseDto buildForError(Exceptions exception) {
        UserLoginResponseDto dto = new UserLoginResponseDto();
        dto.setError(exception.getType());
        return dto;
    }

    private User getUser(UserLoginRequestDto userLoginRequest) {
        return userRepository.findByUsername(userLoginRequest.getUsername());
    }
}
