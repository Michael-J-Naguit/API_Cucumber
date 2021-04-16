package hooks;

import contexts.DataContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import models.MyFile_model;
import tools.RestAssuredExtension;

public class SampleJSONHandlingHooks {
    private DataContext _DataContext;

    public SampleJSONHandlingHooks(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before (value="@jackson")
    public void JacksonTestSetup(){

    }
}
