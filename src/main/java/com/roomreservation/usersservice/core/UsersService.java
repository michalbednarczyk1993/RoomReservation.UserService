package com.roomreservation.usersservice.core;

import org.springframework.stereotype.Service;

@Service
public class UsersService {

    UsersRepository usersRepository;

    UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void register(UserDto newUser) {
        usersRepository.save(newUser.toEntity());
    }

    public UserDto getUser(Integer id) {
        return UserDto.toDto(usersRepository.getReferenceById(id));
    }

    public void update(Integer id, UserDto updatedUser) {
    }

    public void delete(Integer id) {
    }

    public String login(LoginRequestDto loginRequest) {
        return null; // token
    }
}
