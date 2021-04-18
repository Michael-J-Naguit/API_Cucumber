package hooks;

import contexts.DataContext;
import io.cucumber.java.Before;

public class SampleJSONHandlingHooks {

    private DataContext _DataContext;

    public SampleJSONHandlingHooks(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before (value="@jackson")
    public void JacksonTestSetup() {
        var test = "sample";
    }
}
