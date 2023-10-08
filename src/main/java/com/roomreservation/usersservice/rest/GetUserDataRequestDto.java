package com.roomreservation.usersservice.rest;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDataRequestDto {
    private Integer userId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
