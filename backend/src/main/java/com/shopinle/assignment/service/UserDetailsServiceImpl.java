package com.shopinle.assignment.service;


import com.shopinle.assignment.model.User;
import com.shopinle.assignment.repository.UserRepository;
import com.shopinle.assignment.security.jwt.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        return user == null ? null : new AuthenticatedUser(user.getId(),user.getUsername(),user.getEmail(),user.getPassword());
    }
}
