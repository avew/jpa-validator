package io.github.avew.domain;

import io.github.avew.validator.Npwp;
import io.github.avew.validator.OneOf;
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

    @OneOf({0, 9, 3})
    private int facilities;
}
