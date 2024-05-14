package pl.urbanik.school.model;

import lombok.*;

import java.util.List;

/**
 * @author kurbanik
 */

@Getter
@Setter
@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
public class FullSchoolResponse {

    private String name;
    private String email;
    private List<Student> students;
}
