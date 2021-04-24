package tools;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {

    public static RequestSpecification Request;

    public RestAssuredExtension() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }

    public ResponseOptions<Response> GetOps(String url, Map<String, String> pathParams) {
        Request.pathParams(pathParams); // /{id}
        return Request.get(url);
    }

    public ResponseOptions<Response> GetOps(String url, String token) {
        Request.header(new Header("Authorization", "Bearer " + token));
        return Request.get(url);
    }

    public ResponseOptions<Response> GetOps(String url, Map<String, String> queryParams, String token) {
        Request.header(new Header("Authorization", "Bearer " + token));
        Request.queryParams(queryParams); // ?id=1
        return Request.get(url);
    }

    public ResponseOptions<Response> PostOps(String url, Map<String, String> pathParams,
                                                                  Map<String, String> body) {

        Request.pathParams(pathParams);
        Request.body(body);
        return Request.post(url);
    }

    public ResponseOptions<Response> PostOps(String url, Map<String, String> body) {

        Request.body(body);
        try {
            return Request.post(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseOptions<Response> DeleteOps(String url, Map<String, String> pathParams) {
        Request.pathParams(pathParams);
        return Request.delete(url);
    }

    public ResponseOptions<Response> PutOps(String url, Map<String, String> pathParams, Map<String, String> body) {
        Request.pathParams(pathParams);
        Request.body(body);
        return Request.put(url);
    }
}
