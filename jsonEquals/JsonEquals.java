package jsonEquals;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Map;

//utilizando Gson
public class JsonEquals {

    @Test
    public void compareJsonTrue(){
        JsonParser parser = new JsonParser();
        JsonElement json1 = parser.parse("{\n" +
                                            "  \"Name\":\"Paola\",\n" +
                                            "  \"LastName\":\"Vilaseca\"\n" +
                                            "}");
        JsonElement json2 = parser.parse("{\n" +
                                            "  \"Name\":\"Paola\",\n" +
                                            "  \"LastName\":\"Vilaseca\"\n" +
                                            "}");
        Assertions.assertEquals(json1, json2, "Error");
    }

    @Test
    public void compareJsonFalse(){
        JsonParser parser = new JsonParser();
        JsonElement json1 = parser.parse("{\n" +
                                                "  \"Name\":\"Paola\",\n" +
                                                "  \"LastName\":\"Vilaseca\"\n" +
                                                "}");
        JsonElement json2 = parser.parse("{\n" +
                                                "  \"Name\":\"Vilaseca\",\n" +
                                                "  \"LastName\":\"Paola\"\n" +
                                                "}");
        Assertions.assertNotEquals(json1, json2, "Error");
    }

    @Test
    public void compareJsonMissingAttribute() {
        String json1 = "{\"name\":\"Paola\", \"lastname\":\"Vilaseca\", \"City\":\"cbba\"}";
        String json2 = "{\"lastname\":\"Vilaseca\", \"Age\":\"22\", \"name\":\"Paola\"}";

        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> firstMap = g.fromJson(json1, mapType);
        Map<String, Object> secondMap = g.fromJson(json2, mapType);
        System.out.println(Maps.difference(firstMap, secondMap));
    }
}
