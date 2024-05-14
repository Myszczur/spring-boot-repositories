package pl.urbanik.school.model;

import lombok.*;

/**
 * @author kurbanik
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Student {

    private String firstname;
    private String lastname;
    private String email;
}
