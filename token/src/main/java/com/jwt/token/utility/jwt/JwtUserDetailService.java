package com.jwt.token.utility.jwt;

import com.jwt.token.entity.UserEntity;
import com.jwt.token.entity.UserPrincipal;
import com.jwt.token.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) { throw new UsernameNotFoundException("No user found."); }

        return new UserPrincipal(userEntity);
    }

}
