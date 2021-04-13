package steps;

import com.google.common.io.Files;
import contexts.DataContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import tools.RestAssuredExtension;

import java.io.File;
import java.io.IOException;

public class CommonSteps {

    private DataContext _DataContext;

    public CommonSteps(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before
    public void TestSetup(Scenario scenario){
        _DataContext.restAssuredExtension = new RestAssuredExtension();
        _DataContext.scenario = scenario.getName();
    }

    @After
    public void WriteJSONResponseToFile() throws IOException {
        // Remove Special Characters from Scenario Name
        String scenarioName= _DataContext.scenario;
        scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "");

        String responseAsString;
        if (_DataContext.response != null) {
            // Getting response as a string and writing in to a file
            responseAsString = _DataContext.response.body().asString();

            // Converting in to byte array before writing
            byte[] responseAsStringByte = responseAsString.getBytes();

            // Creating a target file
            File targetFileForString = new File("src/main/resources/" + scenarioName + ".json");

            // Writing into files
            Files.write(responseAsStringByte, targetFileForString);
        }
    }
}
