package tools;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtV2 {
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    public RestAssuredExtV2(String uri, String method) {
        //Formulate the API url
        this.url = "http://localhost:3000" + uri;
        this.method = method;
    }

    private ResponseOptions<Response> ExecuteAPI() {

        RequestSpecification requestSpec = builder.build();
        Response response;
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpec);
        if (this.method.equalsIgnoreCase("POST")) {
            return request.post(this.url);
        } else if (this.method.equalsIgnoreCase("DELETE")) {
            return request.delete(this.url);
        } else if (this.method.equalsIgnoreCase("GET")) {
            return request.get(this.url);
        }
        return null;
    }

    public ResponseOptions<Response> ExecuteWithBody(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> queryPath) {
        builder.addPathParams(queryPath);
        return ExecuteAPI();
    }
}
