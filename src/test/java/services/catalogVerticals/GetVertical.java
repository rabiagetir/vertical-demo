package services.catalogVerticals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.VerticalMetaRequest;
import models.request.VerticalRequest;
import models.response.VerticalResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetVertical {

    @Test
    public void createVertical() throws JsonProcessingException {

        Random random = new Random();
        int upperBound = 100;
        int value = random.nextInt(upperBound);


        VerticalMetaRequest tr = new VerticalMetaRequest("RabiaAutomation" + value, "RabiaAutomation" + value, "tr", "RabiaAutomation-tr" + value);
        VerticalMetaRequest eng = new VerticalMetaRequest("RabiaAutomation" + value, "RabiaAutomation" + value, "en", "RabiaAutomation-eng" + value);


        ArrayList<VerticalMetaRequest> Metas = new ArrayList<VerticalMetaRequest>();
        Metas.add(tr);
        Metas.add(eng);


        VerticalRequest verticalRequest = new VerticalRequest("RabiaAutomation" + value, "RabiaAutomation" + value, "temp/images/kadAngiyimGG1pha.png", Metas);

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequestValue = mapper.writeValueAsString(verticalRequest);

        //String jsonRequestValue = new Gson().toJson(verticalRequest);

        /*given().log().all()
                .header("Content-Type", "application/json")
                .body(jsonRequestValue)
                .when().post("https://locals-menu-service.artisandev.getirapi.com/v1/verticals")
                .then()
                .statusCode(201).log().all(); */

        RequestSpecification requestSpecification = given().contentType(ContentType.JSON)
                .body(jsonRequestValue).log().all();
        Response response = requestSpecification.post("https://locals-menu-service.artisandev.getirapi.com/v1/verticals");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int id = jsonPathEvaluator.get("id");
        System.out.println(id);


    }
}
