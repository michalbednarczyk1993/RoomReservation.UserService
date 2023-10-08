package com.roomreservation.usersservice.core;

import com.roomreservation.usersservice.exceptions.UserAlreadyExistException;
import com.roomreservation.usersservice.rest.AuthRegisterRequestDto;
import com.roomreservation.usersservice.rest.JwtAuthenticationResponse;
import com.roomreservation.usersservice.rest.LoginRequestDto;
import com.roomreservation.usersservice.rest.RegisterRequestDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

    UsersRepository usersRepository;

    @Value("${auth-service.base-url}")
    String authServiceUrl;

    UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public JwtAuthenticationResponse register(RegisterRequestDto newUser) throws UserAlreadyExistException{
        if (usersRepository.getReferenceByEmail(newUser.email()) != null)
            throw new UserAlreadyExistException();

        usersRepository.save(newUser.toEntity());

        String baseUrl = authServiceUrl + "/auth/register-user";
        AuthRegisterRequestDto requestDto = new AuthRegisterRequestDto(newUser.email(), newUser.password(), newUser.role());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(baseUrl, requestDto, JwtAuthenticationResponse.class).getBody();
    }

    public RegisterRequestDto getUser(Integer id) {
        String baseUrl = authServiceUrl + "/auth/register-user";
        AuthRegisterRequestDto requestDto = new AuthRegisterRequestDto(newUser.email(), newUser.password(), newUser.role());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(baseUrl, requestDto, JwtAuthenticationResponse.class).getBody();

        // request to AuthService to get user role
        // return (Role, email)
        // if email == id.user.email -> user can view its own account data
        // if role is STUFF or ADMIN -> have access
        // else 403

        return RegisterRequestDto.toDto(usersRepository.getReferenceById(id));
    }

    public void update(Integer id, RegisterRequestDto updatedUser) {
    }

    public void delete(Integer id) {
    }
}
