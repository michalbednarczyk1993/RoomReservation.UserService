package com.roomreservation.usersservice.rest;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {
    private String email;
    private String password;
    private String role;
}
