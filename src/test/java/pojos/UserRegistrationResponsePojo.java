package pojos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserRegistrationResponsePojo {
    private Integer id;
    private String token;
    private String error;

    @Tolerate
    public UserRegistrationResponsePojo() {

    }
}
