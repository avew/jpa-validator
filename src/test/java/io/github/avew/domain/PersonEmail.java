package io.github.avew.domain;

import io.github.avew.validator.EmailConstraintValidation;
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
public class PersonEmail {

    @EmailConstraintValidation
    @NotNull
    private String email;
}
