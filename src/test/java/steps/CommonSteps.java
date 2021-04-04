package steps;

import contexts.DataContext;
import io.cucumber.java.Before;
import tools.RestAssuredExtension;

public class CommonSteps {

    private DataContext _DataContext;

    public CommonSteps(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before
    public void TestSetup(){
        _DataContext.restAssuredExtension = new RestAssuredExtension();
    }
}
