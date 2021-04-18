package apis.services;

import pojos.UserInformationPojo;
import pojos.UserRegistrationPojo;
import pojos.UserRegistrationResponsePojo;

public interface UserService {

    UserRegistrationResponsePojo createUser(Integer status, UserRegistrationPojo userRegistrationPojo);

    UserInformationPojo getUser(Integer status, Integer id);

    void deleteUser(Integer status, Integer id);
}
