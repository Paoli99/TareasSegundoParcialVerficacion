package itemRestAssured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ItemRestAssured {

    @Test
    public void  createItem(){

        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body("{\n" +
                    "  \"Content\":\"New Item 2\",\n" +
                    "}")
            .log().all()
        .when()
            .post("https://todo.ly/api/items.json")
        .then()
            .log().all();
    }

    @Test
    public void createItemJSONObjet(){
        JSONObject body = new JSONObject();
        body.put("Content","ItemJSONNew");

        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body(body.toString())
            .log().all()
        .when()
            .post("https://todo.ly/api/items.json")
        .then()
            .log().all();

    }

    @Test
    public void createItemExternalFile(){
    String pathProject = new File("").getAbsolutePath();
        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body(new File(pathProject+"/src/test/resources/itemBody.json"))
            .log().all()
        .when()
            .post("https://todo.ly/api/items.json")
        .then()
            .log().all();

    }

}
