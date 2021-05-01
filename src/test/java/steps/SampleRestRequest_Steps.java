package steps;

import contexts.DataContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import tools.RestAssuredExtV2;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SampleRestRequest_Steps {

    private final DataContext _DataContext;

    public SampleRestRequest_Steps(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String uri, DataTable table) {
        // Convert Cucumber table to List
        List<List<String>> rows = table.asLists(String.class);

        // Set Path Parameter
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("id", rows.get(1).get(0));

        // Using RestAssuredExtV2
        RestAssuredExtV2 restAssuredExtV2 = new RestAssuredExtV2(uri, "GET");
        _DataContext.response = restAssuredExtV2.ExecuteWithPathParams(pathParams);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String author) {
        assertEquals(_DataContext.response.getBody().jsonPath().get("author"), author);

        // If Model is defined for Posts (restAssured deserialization process)
        // response.getBody().as(Posts[].class);
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
        _DataContext.response = _DataContext.restAssuredExtension.PostOps(url, pathParams, body);

    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertEquals(_DataContext.response.getBody().jsonPath().get("name"), name);
    }

    @Given("I perform POST operation for {string}, with body")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) {
        // Convert Cucumber table to List
        List<List<String>> rows = table.asLists(String.class);

        // Set Body (dynamically)
        HashMap<String, String> body = new HashMap<>();
        for (int i=0; i<(rows.get(0).size()); i++) // Iterate through Header Size
        {
            body.put(rows.get(0).get(i), rows.get(1).get(i)); // Get value from each row and each column
        }

        // Perform Post Operation
        _DataContext.response = _DataContext.restAssuredExtension.PostOps(url, body);
    }

    @And("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) {
        // Convert Cucumber table to List
        List<List<String>> rows = table.asLists(String.class);

        // Set Path Parameter
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("id", rows.get(1).get(0));

        // Perform Delete Operation
        _DataContext.response = _DataContext.restAssuredExtension.DeleteOps(url, pathParams);
    }

    @Then("I should not see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) {
        assertNotEquals(_DataContext.response.getBody().jsonPath().get("name"), title);
    }

    @And("I perform PUT operation for {string}")
    public void iPerformPUTOperationFor(String url, DataTable table) {
        // Convert Cucumber table to List
        List<List<String>> rows = table.asLists(String.class);

        // Set Path Parameter
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("id", rows.get(1).get(0));

        // Set Body (hardcoded)
        HashMap<String, String> body = new HashMap<>();
        body.put("title", rows.get(1).get(1));
        body.put("author", rows.get(1).get(2));

        // Perform Put Operation
        _DataContext.response = _DataContext.restAssuredExtension.PutOps(url, pathParams, body);
    }

}
