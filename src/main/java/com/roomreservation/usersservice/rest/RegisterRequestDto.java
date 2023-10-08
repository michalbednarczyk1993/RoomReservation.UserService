package com.roomreservation.usersservice.rest;

import com.roomreservation.usersservice.core.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

@Builder
@ApiModel(description = "Szczegóły na temat rezerwacji")
public record RegisterRequestDto(
        @ApiModelProperty(value = "Imię") @NotNull  String name,
        @ApiModelProperty(value = "Nazwisko") @NotNull String surname,
        @ApiModelProperty(value = "Adres e-mail") @NotNull  String email,
        @ApiModelProperty(value = "Hasło") @NotNull String password,
        @ApiModelProperty(value = "Numer telefonu") @NotNull String phoneNumber,
        @ApiModelProperty(value = "Rola") @NotNull String role
) {

    public static RegisterRequestDto toDto(@NotNull User entity) {
        return null;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }
}
