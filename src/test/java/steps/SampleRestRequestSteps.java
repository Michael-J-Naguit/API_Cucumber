package steps;

import contexts.DataContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SampleRestRequestSteps {

    private final DataContext _DataContext;

    public SampleRestRequestSteps(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
        _DataContext.response = _DataContext.restAssuredExtension.GetOps(url);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String author) {
        assertThat(_DataContext.response.getBody().jsonPath().get("author"), equalTo(author));
    }

    @Given("I perform POST operation for {string}, {string} with body")
    public void iPerformPOSTOperationForWithBody(String url, String num, DataTable table) {
        // Convert Cucumber table to List
        List<List<String>> rows = table.asLists(String.class);

        // Set Body
        HashMap<String, String> body = new HashMap<>();
        for (int i=0; i<(rows.get(0).size()); i++)
        {
            body.put(rows.get(0).get(i), rows.get(1).get(i));
        }

        // Set Path Parameter
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", num);

        // Perform Post Operation
        _DataContext.response = _DataContext.restAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParams, body);

    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(_DataContext.response.getBody().jsonPath().get("name"), equalTo(name));
    }
}