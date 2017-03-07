package com.shopinle.assignment.security.jwt;

import com.shopinle.assignment.dto.login.UserLoginRequestDto;
import com.shopinle.assignment.model.UserProfile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService  {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${shopinle.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    public String getAuthenticationToken(UserLoginRequestDto userLoginRequestDto){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getUsername(),
                        userLoginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedUser userDetails = (AuthenticatedUser) this.userDetailsService.loadUserByUsername(userLoginRequestDto.getUsername());
        String token = this.tokenUtils.generateToken(userDetails);

        return !StringUtils.isEmpty(token) ? token : null;
    }

    public UserProfile retrieveUser(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);

        String username = tokenUtils.getUsernameFromToken(token);
        String email = tokenUtils.getEmailFromToken(token);

        return new UserProfile(username,email);

    }

}
