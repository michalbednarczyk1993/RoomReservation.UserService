package com.roomreservation.usersservice.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

@Builder
@ApiModel(description = "Szczegóły na temat rezerwacji")
public record UserDto(
        @ApiModelProperty(value = "") @Null(groups = {OnUpdate.class}) @NotNull Date startDate
) {

    public interface OnCreate{}
    public interface OnUpdate{}

    public static UserDto toDto(@NotNull User entity) {
        return null;
    }

    public User toEntity() {
        return User.builder()
                .build();
    }

    public User updateEntity(User entity) {
        //if (currency != null) entity.setCurrency(currency);
        return entity;
    }
}
