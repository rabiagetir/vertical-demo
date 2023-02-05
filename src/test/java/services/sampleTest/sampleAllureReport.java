package services.sampleTest;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
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
        attachment(requestSpecification, baseUrl, response);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    public String attachment (RequestSpecification httpRequest, String baseUrl, Response response){
        String html = "URL= " + baseUrl + "\n\n" +
                "request header= " + ((RequestSpecificationImpl) httpRequest).getHeaders() + "\n\n" +
                "request body= " + ((RequestSpecificationImpl) httpRequest).getBody() + "\n\n" +
                "response body= " + response.getBody().asString();

        Allure.addAttachment("request detail", html);
        return  html;
    }
}
