package com.jwt.token.service;

import com.jwt.token.dto.UserRegisterRequest;
import com.jwt.token.dto.UserRegisterResponse;
import com.jwt.token.dto.ValidateLoginResponse;
import com.jwt.token.entity.UserEntity;
import com.jwt.token.repository.UserRepository;
import com.jwt.token.utility.jwt.JwtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Value("${credential-for-create-user}")
    private String privateString;

    public UserRegisterResponse registerUser(UserRegisterRequest req, String userIp) {

        UserRegisterResponse response = new UserRegisterResponse();

        try {

            UserEntity byUsername = userRepository.findByUsername(req.getUsername());

            if (!privateString.equals(req.getPrivateKey())) {

                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMessage("The admin key is not correct.");
                return response;

            }

            if (byUsername != null) {
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMessage("This user already existed.");
                return response;
            }

            UserEntity user = new UserEntity();
            BeanUtils.copyProperties(req, user);
            user.beforeSave();
            user.setRequester(userIp);
            user.setPassword(encoder.encode(req.getPassword()));

            userRepository.save(user); // Save user to database

            BeanUtils.copyProperties(user, response);
            response.setEffectiveDate(user.getCreateDate());

            response.setCode(HttpStatus.OK.value());
            response.setMessage("User created successfully.");

        }
        catch (Exception ex) {

            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Unable to create user. " + ex.getMessage());

        }

        return response;

    }

    public ValidateLoginResponse verify(UserRegisterRequest req) {

        ValidateLoginResponse validation = new ValidateLoginResponse();

        try {

            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

            if (authentication.isAuthenticated()) {
                validation.successLogin(jwtService.generateToken(req.getUsername()));
                return validation;
            }
            validation.failLogin("Invalid credential.");
        }
        catch(Exception ex) {
            validation.failLogin(ex.getMessage());
        }

        return validation;

    }
}
