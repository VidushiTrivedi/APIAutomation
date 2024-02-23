package validator;

import io.restassured.response.Response;
import models.ContentModel;
import models.GetModel;
import models.PostModel;
import org.testng.Assert;

public class ResponseValidation {

    public static void verifyGetResponse(Response response){
        GetModel model = response.getBody().as(GetModel.class);  //Storing data in PostModel using Restassured as() method
        int flag = 1;
        for (ContentModel contentModel : model.content) {  // can be encapsulated in another method
            if (contentModel.id == null || contentModel.name == null || contentModel.tech_type_id == null) {
                flag = 0;
                break;
            }
        }
        if (flag == 0) {
            Assert.assertTrue(false, "mandatory fields have null value");
        } else
            Assert.assertTrue(true);

    }

    public static void verifyPostAPIResponse(Response response){
        PostModel res = response.as(PostModel.class);       //Storing data in PostModel using Restassured as() method
        Assert.assertEquals(res.firstName, "Vidushi");
        Assert.assertEquals(res.lastName, "Trivedi");

    }

    public static void verifyPUTAPIResponse(Response response){
        PostModel res = response.as(PostModel.class);  //Storing data in PostModel using Restassured as() method
        Assert.assertEquals(res.lastName, "Trivedi2");

    }

    public static void verifyDeleteAPIResponse(Response response, String id){
        Assert.assertEquals(response.jsonPath().getString("id"), id);
    }
}
