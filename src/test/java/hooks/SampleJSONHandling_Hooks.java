package hooks;

import contexts.DataContext;
import io.cucumber.java.Before;

public class SampleJSONHandling_Hooks {

    private DataContext _DataContext;

    public SampleJSONHandling_Hooks(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Before (value="@jackson")
    public void JacksonTestSetup() {
        String test = "sample";
    }
}
