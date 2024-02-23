import constants.ApiEndPoints;
import constants.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {

    static Map<String,String> headers = new HashMap<>();

    @BeforeSuite
    public void beforeSuite(){
        headers.put("Authorization","Bearer "+ Constants.token);
        RestAssured.baseURI= ApiEndPoints.baseURL;
    }
}
