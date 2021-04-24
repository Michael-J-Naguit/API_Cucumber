package steps;

import models.Address_Model;
import models.Location_Model;
import models.MyFile_Model;
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
import java.util.Locale;

import static org.junit.Assert.*;

public class SampleJSONHandling_Steps {

    private final DataContext _DataContext;

    public SampleJSONHandling_Steps(DataContext dataContext) {
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
        assertEquals(_DataContext.tempResponse, name);
    }

    @And("I should see JSON array length is {int}")
    public void iShouldSeeJSONArrayLengthIs(int length) {
        assertEquals(_DataContext.jsonArray.length(), length);
    }

    @Given("I have a JSON from a file converted into Input Stream")
    public void iHaveAJSONFromAFileConvertedIntoInputStream() {
        _DataContext.resource = "/testData/pageInfo.json";
        InputStream is = SampleJSONHandling_Steps.class.getResourceAsStream(_DataContext.resource);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + _DataContext.resource);
        }
        JSONTokener tokens = new JSONTokener(is);
        _DataContext.jsonObject  = new JSONObject(tokens);
    }

    @Then("I should see {string} is {string}")
    public void iShouldSeeIs(String name, String value) {
        assertEquals(_DataContext.jsonObject.getJSONObject("pageInfo").getString(name), value);
    }

    @Given("I have a JSON from a file converted into POJO {string}")
    public void iHaveAJSONFromAFileConvertedIntoString(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (fileName.contains("myFile"))
            _DataContext.myFile_pojo = objectMapper.readValue(new File("src/main/resources/testData/"+fileName), MyFile_Model.class);
        else if (fileName.contains("location"))
            _DataContext.location_pojo = objectMapper.readValue(new File("src/main/resources/testData/"+fileName), Location_Model[].class);
    }

    @Then("I should see {string} is {string} by using Jackson Mapper")
    public void iShouldSeeIsByUsingJacksonMapper(String name, String value) {
        String expectedValue = "";
        if (name.equalsIgnoreCase("age"))
            expectedValue = Integer.toString(_DataContext.myFile_pojo.getAge());
        assertEquals("Message if Fail", expectedValue, value);
    }

    @Then("Verify values from JSON Array")
    public void verifyValuesFromJSONArray() {
        Address_Model firstAddressPrimary = _DataContext.location_pojo[0].getAddress().stream().filter(x -> x.getType()
                .equalsIgnoreCase("primary")).findFirst().orElse(null);

        Address_Model secondAddressPrimary = _DataContext.location_pojo[1].getAddress().stream().filter(x -> x.getType()
                .equalsIgnoreCase("primary")).findFirst().orElse(null);

        String firstAddressCountry = _DataContext.location_pojo[0].getCountry();

        assertEquals("18A", firstAddressPrimary.getFlat_no());
        assertEquals("111", secondAddressPrimary.getFlat_no());
        assertEquals("Philippines", firstAddressCountry);
    }
}
