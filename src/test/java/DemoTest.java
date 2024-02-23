import constants.ApiEndPoints;
import constants.Constants;
import enums.RequestType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ContentModel;
import models.GetModel;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtil;
import validator.ResponseValidation;

import java.util.HashMap;
import java.util.Map;

public class DemoTest extends BaseRequest {

    ThreadLocal<String> id = new ThreadLocal<>();
    Map<String, String> map = new HashMap<>();


    @BeforeClass
    public void beforeClass() {
        map.put("app-id", Constants.appId);
    }

    @Test(description = "verify data of registered users") // Add description as well.
    public void verifyUserData() { // Names can be more meaningful
        RestAssured.baseURI = ApiEndPoints.baseURL;
        Response response = RestUtil.sendRequest(BaseRequest.headers,null, ApiEndPoints.getEndPoint,RequestType.GET);
        ResponseValidation.verifyGetResponse(response);


    }

    @Test(description = "verify user is created successfully using POST API")
    public void verifyUserCreation() {
        RestAssured.baseURI = ApiEndPoints.baseURL2;  //use json object, create a method to generate random email
        Response response = RestUtil.sendRequest(map, DataProvider.createUserDataBody(), ApiEndPoints.createUserEndPoint, RequestType.POST);
        ResponseValidation.verifyPostAPIResponse(response);
        PostModel res = response.as(PostModel.class);       //Storing data in PostModel using lombok library
        id.set(res.id);
    }

    @Test(dependsOnMethods = "verifyUserCreation",description = "update data of already existing user")
    public void verifyUpdatedUserData() {
        RestAssured.baseURI = ApiEndPoints.baseURL2;
        Response response = RestUtil.sendRequest(map, DataProvider.createUserDataBody(), ApiEndPoints.updateUserEndPoint + id.get(), RequestType.PUT);
        ResponseValidation.verifyPUTAPIResponse(response);

    }

    @Test(dependsOnMethods = "verifyUpdatedUserData",description = "delete an existing user")
    public void verifyUserDeletion() {
        RestAssured.baseURI = ApiEndPoints.baseURL2;
        Response response = RestUtil.sendRequest(map, DataProvider.createUserDataBody(),ApiEndPoints.deleteUserEndPoint + id.get(),RequestType.DELETE);
        ResponseValidation.verifyDeleteAPIResponse(response,id.get());

    }

}
