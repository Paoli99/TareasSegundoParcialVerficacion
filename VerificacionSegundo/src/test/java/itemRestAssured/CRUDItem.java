package itemRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItem {
    @Test
    public void crudProject(){
        // Create
        JSONObject body = new JSONObject();
        body.put("Content","ItemJSON2");

        Response response=given()
                .auth()
                .preemptive()
                .basic("paola_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("ItemJSON2"))
                .log().all();

        int idItem =response.then().extract().path("Id");

        // Read
        response=given()
                .auth()
                .preemptive()
                .basic("paola_api@api.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("ItemJSON2"))
                .log().all();

        // Update
        body.put("Checked",true);
        response=given()
                .auth()
                .preemptive()
                .basic("paola_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Checked",equalTo(true))
                .log().all();
        // Delete
        response=given()
                .auth()
                .preemptive()
                .basic("paola_api@api.com","12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Checked",equalTo(true))
                .body("Deleted",equalTo(true))
                .log().all();
    }

}
