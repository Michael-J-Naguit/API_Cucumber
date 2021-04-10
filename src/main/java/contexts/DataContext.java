package contexts;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import tools.RestAssuredExtension;
import io.cucumber.java.Scenario;

public class DataContext {
    public ResponseOptions<Response> response;
    public RestAssuredExtension restAssuredExtension;
    public String scenario;
}
