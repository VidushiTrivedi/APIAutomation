package utils;

import enums.RequestType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.Map;


public class RestUtil {

    public static Response sendRequest(Map<String, String> headerMap, Object body, String url, RequestType requestType) {
        switch (requestType.getRequestType().toLowerCase()) {
            case "get":
                return RestAssured.given().log().all().when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.ANY)
                    .headers(headerMap)
                    .get(url);
            case "post":
                return RestAssured.given().log().all().when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.ANY)
                    .headers(headerMap)
                    .body(body.toString())
                    .post(url);
            case "put":
                return RestAssured.given().log().all().when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.ANY)
                    .headers(headerMap)
                    .body(body.toString())
                    .put(url);

            case "delete":
                return RestAssured.given().log().all().when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.ANY)
                    .headers(headerMap)
                    .delete(url);

            default:

                return null;
         }
        }

    /*public static Response sendPostRequest(Map<String, String> headerMap, String apiUrl, Object body) { //Make a common method
        return RestAssured.given().log().all().when()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .headers(headerMap)
            .body(body.toString())
            .post(apiUrl);
    }

    public static Response sendPutRequest(Map<String, String> headerMap, String apiUrl, Object body) {
        return RestAssured.given().log().all().when()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .headers(headerMap)
            .body(body.toString())
            .put(apiUrl);
    }

    public static Response sendGetRequest(Map<String, String> headerMap,String apiUrl) {
        return RestAssured.given().log().all().when()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .headers(headerMap)
            .get(apiUrl);
    }
    public static Response sendDeleteRequest(Map<String, String> headerMap,String apiUrl) {
        return RestAssured.given().log().all().when()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .headers(headerMap)
            .delete(apiUrl);
    }*/
}
