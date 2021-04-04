package contexts;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import tools.RestAssuredExtension;

public class DataContext {
    public ResponseOptions<Response> response;
    public RestAssuredExtension restAssuredExtension;
}
