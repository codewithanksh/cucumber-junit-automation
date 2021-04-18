package apis.services.Impl;

import apis.constants.APIEndpoints;
import apis.services.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import pojos.UserInformationPojo;
import pojos.UserRegistrationPojo;
import pojos.UserRegistrationResponsePojo;

import static io.restassured.RestAssured.given;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        RestAssured.baseURI = APIEndpoints.BASE_URL;
    }

    @Override
    public UserRegistrationResponsePojo createUser(Integer status, UserRegistrationPojo userRegistrationPojo) {
        Response response =  given().contentType(ContentType.JSON).body(userRegistrationPojo)
                .post(APIEndpoints.CREATE_USER)
                .then().log().ifValidationFails().statusCode(status)
                .assertThat().header("Content-Type","application/json; charset=utf-8")
                .extract().response();


        if(response.statusCode() == HttpStatus.SC_CREATED) {
            return response.jsonPath().getObject("$",UserRegistrationResponsePojo.class);
        } else {
            return response.jsonPath().getObject("$",UserRegistrationResponsePojo.class);
        }
    }

    @Override
    public UserInformationPojo getUser(Integer status, Integer id) {
        Response response =  given().contentType(ContentType.JSON).pathParam("id",id)
                .get(APIEndpoints.GET_USER)
                .then().log().ifValidationFails().statusCode(status)
                .assertThat().header("Content-Type","application/json; charset=utf-8")
                .extract().response();


        if(response.statusCode() == HttpStatus.SC_OK) {
            return response.jsonPath().getObject("data",UserInformationPojo.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Integer status, Integer id) {
        given().contentType(ContentType.JSON).pathParam("id",id)
                .delete(APIEndpoints.DELETE_USER)
                .then().log().ifValidationFails().statusCode(status)
                .extract().response();
    }
}
