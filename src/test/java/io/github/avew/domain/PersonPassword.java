package io.github.avew.domain;

import io.github.avew.validator.PasswordConstraintValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonPassword {

    @PasswordConstraintValidation
    @NotNull
    private String password;
}
