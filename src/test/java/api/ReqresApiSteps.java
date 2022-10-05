package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReqresApiSteps {
    public static void createUser() throws IOException {

        JSONObject requestBody = new JSONObject(new String(Files.readAllBytes(Paths.get("src/json/reqres.json"))));

        Response response = given()
                .log().all()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(requestBody.put("name", "Tomato"))
                .body(requestBody.put("job", "Eat maket"))
                .body(requestBody.toString())
                .when()
                    .post("api/users")
                .then().log().all()
                    .statusCode(201)
                    .extract().response();

        JSONObject newUserBody = new JSONObject(response.getBody().asString());

        Assertions.assertEquals(newUserBody.getString("name"), requestBody.getString("name"), "Имена не совпадают");
        Assertions.assertEquals(newUserBody.getString("job"), requestBody.getString("job"), "Место работы не совпадает");
        Assertions.assertNotNull(newUserBody.getString("id"),"Id пустое");
        Assertions.assertNotNull(newUserBody.getString("createdAt"),"Дата создания пустая");
    }
}
