package pojos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserRegistrationPojo {
    private String email;
    private String password;

    @Tolerate
    public UserRegistrationPojo() {

    }
}
