package com.finalproject.secondhand.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private @NotNull String oldPassword;
    private @NotNull String newPassword;
    private @NotNull String confirmPassword;

}
