package stepdefs;

import apis.services.Impl.UserServiceImpl;
import apis.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pojos.UserInformationPojo;
import pojos.UserRegistrationPojo;
import pojos.UserRegistrationResponsePojo;

import java.util.List;
import java.util.Map;


public class UserSteps {

    static UserService userService = new UserServiceImpl();
    static UserRegistrationPojo userRegistrationPojo;
    static UserInformationPojo userInformationPojo;
    static Integer id = -1;

    @Given("User provides following information for registration")
    public void user_provides_following_information_for_registration(List<Map<String,String>> userRegistrationMap) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String tempjson = mapper.writeValueAsString(userRegistrationMap.get(0));
        userRegistrationPojo = mapper.readValue(tempjson,UserRegistrationPojo.class);
    }

    @Then("User should be created successfully")
    public void user_should_be_created_successfully() {
        UserRegistrationResponsePojo userRegistrationResponsePojo = userService.createUser(200,userRegistrationPojo);

        // Assertions
        Assert.assertNotEquals(userRegistrationResponsePojo.getId() , null);
        Assert.assertNotEquals(userRegistrationResponsePojo.getToken() , "");
    }

    @Then("User should be not created with message {string}")
    public void user_should_be_not_created(String message) {
        UserRegistrationResponsePojo userRegistrationResponsePojo = userService.createUser(400,userRegistrationPojo);
        Assert.assertEquals(message,userRegistrationResponsePojo.getError());
    }

    @Given("User has the Id {int}")
    public void user_provides_the_id(Integer id) {
        this.id = id;
    }

    @Then("User information should be available with below details")
    public void user_information_should_be_available_with_below_details(List<Map<String,String>> userInformationMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String tempjson = mapper.writeValueAsString(userInformationMap.get(0));
        userInformationPojo = mapper.readValue(tempjson,UserInformationPojo.class);

        UserInformationPojo tempUserInformationPojo = userService.getUser(200,id);

        Assert.assertEquals(userInformationPojo.getId(),tempUserInformationPojo.getId());
        Assert.assertEquals(userInformationPojo.getEmail(),tempUserInformationPojo.getEmail());
        Assert.assertEquals(userInformationPojo.getFirst_name(),tempUserInformationPojo.getFirst_name());
        Assert.assertEquals(userInformationPojo.getLast_name(),tempUserInformationPojo.getLast_name());
        Assert.assertEquals(userInformationPojo.getAvatar(),tempUserInformationPojo.getAvatar());
    }


    @Then("User information should not be available")
    public void user_information_should_not_be_available() {
        userService.getUser(404,id);
    }


    @Given("User having Id {int} should be deleted successfully")
    public void userHavingIdShouldBeDeletedSuccessfully(int id) {
        userService.deleteUser(204,id);
    }

    @Then("User having no Id should not be deleted")
    public void userHavingIdShouldNotBeDeleted() {
        userService.deleteUser(204,-1);
    }
}
