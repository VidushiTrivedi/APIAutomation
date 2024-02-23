import models.PostModel;

import java.util.Random;
import java.util.UUID;

public class DataProvider {

    static PostModel model = new PostModel();

    public static PostModel createUserDataBody(){
        model.firstName= "Vidushi";
        model.lastName = "Trivedi";
        model.email= generateRandomString(7)+"@gmail.com";
        return model;

    }

    public static String generateRandomString(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(length, uuid.length()));
    }
}
