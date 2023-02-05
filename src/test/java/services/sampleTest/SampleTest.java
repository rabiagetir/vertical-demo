package services.sampleTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.VerticalMetaRequest;
import models.request.VerticalRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SampleTest {

    @Test
    public void sample ()
    {
        Response response = RestAssured.get("https://locals-menu-service.artisandev.getirapi.com/v1/verticals");
        System.out.println("ResponseBody: " +response.asString());
        System.out.println("ResponseBody: " +response.getBody().asString());
        System.out.println("StatusCode: " +response.statusCode());
        System.out.println("ContentType: " +response.getHeader("Content-Type"));
        System.out.println("ContentType: " +response.getHeaders());
        System.out.println("Time: " +response.getTime());

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getVerticals ()
    {

        given().log().all()
                .when().get("https://locals-menu-service.artisandev.getirapi.com/v1/verticals")
                .then().statusCode(200).time(lessThanOrEqualTo(1870L)) //millisecond
                .log().all(); // response body yazdır
    }
    @Test
    public void getVerticalDetail ()
    {
        int verticalId = 128;
        given().log().all()
                .when().get("https://locals-menu-service.artisandev.getirapi.com/v1/verticals/"+verticalId)
                .then().statusCode(200).time(lessThan(4000L))
                .body("id", equalTo(verticalId))
                .body("media.key", startsWith("images"))
                .body("media.id", equalTo(215476))
                .body("metas[0].lang", startsWith("tr"))
                .log().all();


    }

    @Test
    public void postCreateVertical ()        // json string ile gönderme
    {



        String requestBody = "{\"title\":\"rraa\",\"shortTitle\":\"rraa\",\"mediaId\":\"temp/images/kadAngiyimGG1pha.png\",\"metas\":[{\"shortTitle\":\"rraa\",\"title\":\"rraa\",\"lang\":\"tr\",\"slug\":\"rraa-tr\"},{\"shortTitle\":\"rraa\",\"title\":\"rraa\",\"lang\":\"en\",\"slug\":\"rraa-en\"}]}";

        given().body(requestBody)
                .contentType(ContentType.JSON).log().all()
                .when().post("https://locals-menu-service.artisandev.getirapi.com/v1/verticals")
                .then().statusCode(201).log().all();



    }

    @Test
    public void postVerticalModel () throws JsonProcessingException {

        Random random = new Random();
        int upperBound = 100;
        int value = random.nextInt(upperBound);



        VerticalMetaRequest tr = new VerticalMetaRequest ("RabiaAutomation"+value, "RabiaAutomation"+value, "tr", "RabiaAutomation-tr"+value);
        VerticalMetaRequest eng = new VerticalMetaRequest ("RabiaAutomation"+value, "RabiaAutomation"+value, "en", "RabiaAutomation-eng"+value);


        ArrayList<VerticalMetaRequest> Metas = new ArrayList<VerticalMetaRequest>();
        Metas.add(tr);
        Metas.add(eng);


        VerticalRequest verticalRequest = new VerticalRequest ("RabiaAutomation"+value,"RabiaAutomation"+value, "temp/images/kadAngiyimGG1pha.png",Metas);

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequestValue = mapper.writeValueAsString(verticalRequest);

        //String jsonRequestValue = new Gson().toJson(verticalRequest);

        given().log().all()
                .header("Content-Type", "application/json")
                .body(jsonRequestValue)
                .when().post("https://locals-menu-service.artisandev.getirapi.com/v1/verticals")
                .then()
                .statusCode(201).log().all();
    }


    @Test

    public void getSpecifiedVerticals () //query params hep given komutunda olcak
    {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("title", "rabia");
        queryParams.put("isDeleted", "true");
        queryParams.put("limit", 2);
        queryParams.put("offset", 0);
        given().log().all().queryParams(queryParams)
                .when().get("https://locals-menu-service.artisandev.getirapi.com/v1/verticals")
                .then().statusCode(200).log().all();
    }

    @Test
    public void deleteVertical ()
    {
        given().log().all()
                .when().delete("https://locals-menu-service.artisandev.getirapi.com/v1/verticals/137")
                .then().statusCode(204).log().all();
    }


}
