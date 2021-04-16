package steps;

import com.google.common.io.Files;
import contexts.DataContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import tools.RestAssuredExtension;

import java.io.File;
import java.io.IOException;

public class Hooks {

    private DataContext _DataContext;

    public Hooks(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before
    public void TestSetup(Scenario scenario){
        _DataContext.restAssuredExtension = new RestAssuredExtension();
        _DataContext.scenario = scenario.getName();
    }

    @After (value="@withResponseBody")
    public void WriteJSONResponseToFile() throws IOException {
        // Remove Special Characters from Scenario Name
        String scenarioName= _DataContext.scenario;
        scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "");

        // Getting response as a string and writing in to a file
        String responseAsString = _DataContext.response.body().asString();

        // Converting in to byte array before writing
        byte[] responseAsStringByte = responseAsString.getBytes();

        // Creating a target file
        File targetFileForString = new File("src/main/resources/testResponse/" + scenarioName + ".json");

        // Writing into files
        Files.write(responseAsStringByte, targetFileForString);
    }

    @Before (value="@jackson")
    public void JacksonTestSetup(){
        var test = "sample";
    }
}
