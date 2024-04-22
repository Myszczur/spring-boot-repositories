package pl.urbanik.callapp.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kurbanik
 */
@Data
@Builder
public class User {

    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String status;
}
