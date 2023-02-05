package services.sampleTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class sampleDataProvider {

    @DataProvider (name = "dataProvider")
    public Object[][] dataProvider ()
    {
        return new Object[][]{
                {139, 200},
                {140,200}
        };

    }

    @Test (dataProvider = "dataProvider")
    public void getVerticalOrderly (int verticalId, int StatusCode)
    {
        RequestSpecification requestSpecification = given().log().all();
        Response response = requestSpecification.when().get("https://locals-menu-service.artisandev.getirapi.com/v1/verticals/"+verticalId);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200, "Correct status code returned");



    }
}
