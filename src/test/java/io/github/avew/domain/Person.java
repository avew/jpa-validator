package io.github.avew.domain;

import io.github.avew.validator.Npwp;
import io.github.avew.validator.OneOfInteger;
import io.github.avew.validator.OneOfString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Npwp
    private String npwp;

    @OneOfInteger({0, 9, 3})
    private int facilities;

    @OneOfString(value = {"L", "l"}, nullable = false)
    private String gender;
}
