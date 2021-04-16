package steps;

import io.cucumber.java.sl.In;
import models.MyFile_model;
import com.fasterxml.jackson.databind.DeserializationFeature;
import contexts.DataContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SampleJSONHandlingSteps {

    private final DataContext _DataContext;

    public SampleJSONHandlingSteps(DataContext dataContext) {
        _DataContext = dataContext;
    }

    @Given("I have JSON in string format")
    public void iHaveJSONInStringFormat() {
        _DataContext.json = "[{\"name\": \"Java\", \"description\": \"Java is a class-based, object-oriented programming language.\"}," +
                "{\"name\": \"Python\", \"description\": \"Python is an interpreted, high-level and general-purpose programming language.\"}, " +
                "{\"name\": \"JS\", \"description\": \"JS is a programming language that conforms to the ECMAScript specification.\"}]";
    }

    @When("I deserialize the JSON into Array")
    public void iDeserializeTheJSONIntoArray() {
        _DataContext.jsonArray = new JSONArray(_DataContext.json);
    }

    @Then("I should see index {int} name is {string}")
    public void iShouldSeeIndexNameIs(int index, String name) {
        _DataContext.tempResponse = _DataContext.jsonArray.getJSONObject(index).getString("name");
        assertThat(_DataContext.tempResponse, equalTo(name));
    }

    @And("I should see JSON array length is {int}")
    public void iShouldSeeJSONArrayLengthIs(int length) {
        assertThat(_DataContext.jsonArray.length(), equalTo(length));
    }

    @Given("I have a JSON from a file converted into Input Stream")
    public void iHaveAJSONFromAFileConvertedIntoInputStream() {
        _DataContext.resource = "/testData/pageInfo.json";
        InputStream is = SampleJSONHandlingSteps.class.getResourceAsStream(_DataContext.resource);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + _DataContext.resource);
        }
        JSONTokener tokens = new JSONTokener(is);
        _DataContext.jsonObject  = new JSONObject(tokens);
    }

    @Then("I should see {string} is {string}")
    public void iShouldSeeIs(String name, String value) {
        assertThat(_DataContext.jsonObject.getJSONObject("pageInfo").getString(name), equalTo(value));
    }

    @Given("I have a JSON from a file converted into String")
    public void iHaveAJSONFromAFileConvertedIntoString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        _DataContext.myFile_model = objectMapper.readValue(new File("src/main/resources/testData/myFile.json"), MyFile_model.class);
    }

    @Then("I should see {string} is {string} by using Jackson Mapper")
    public void iShouldSeeIsByUsingJacksonMapper(String name, String value) {
        assertThat(Integer.toString(_DataContext.myFile_model.getAge()), equalTo(value));
    }
}
