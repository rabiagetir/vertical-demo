package services.sampleTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class sampleAllureReport {
    @Test
    public void getVerticals ()
    {
        String baseUrl = "https://locals-menu-service.artisandev.getirapi.com/v1/verticals";

        RequestSpecification requestSpecification = RestAssured.given()
                        .header("Content-Type", "json")
                                .log().all();
        Response response = requestSpecification.get(baseUrl);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
