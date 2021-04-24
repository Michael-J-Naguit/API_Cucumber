package contexts;

import models.Location_Model;
import models.MyFile_Model;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.RestAssuredExtension;

public class DataContext {
    public ResponseOptions<Response> response;
    public RestAssuredExtension restAssuredExtension;
    public String scenario;
    public String json;
    public String tempResponse;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public String resource;
    public MyFile_Model myFile_pojo;
    public Location_Model[] location_pojo;
}
