package basicRestAssured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BasicRestAssured {

    @Test
    public void  createProject(){

        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body("{\n" +
                    "  \"Content\":\"PaolaRestAssured\",\n" +
                    "  \"Icon\":\"2\"\n" +
                    "}")
            .log().all()
        .when()
            .post("https://todo.ly/api/projects.json")
        .then()
            .log().all();
    }

    @Test
    public void createProjectJSONObjet(){
        JSONObject body = new JSONObject();
        body.put("Content","PaolaJSON");
        body.put("Icon",4);

        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body(body.toString())
            .log().all()
        .when()
            .post("https://todo.ly/api/projects.json")
        .then()
            .log().all();

    }

    @Test
    public void createProjectExternalFile(){
    String pathProject = new File("").getAbsolutePath();
        given()
            .auth()
            .preemptive()
            .basic("paola_api@api.com","12345")
            .body(new File(pathProject+"/src/test/resources/projectBody.json"))
            .log().all()
        .when()
            .post("https://todo.ly/api/projects.json")
        .then()
            .log().all();

    }

}
