package eLkUtils;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import enums.ConfigProperties;
import utilities.PropertyUtils;
import org.testng.Assert;
import io.restassured.response.Response;

public class ELKUtils {

    private ELKUtils() {
    }

    public static void sendDetailsToElk(String testname, String status) {

        if (PropertyUtils.get(ConfigProperties.SENDRESULTTOELK).equalsIgnoreCase("yes")) {
            Map<String, String> map = new HashMap<>();
            map.put("testName", testname);
            map.put("status", status);
            map.put("executionTime", LocalDateTime.now().toString());

            Response response = given().header("Content-Type", "application/json").log().all().body(map)
                    .post(PropertyUtils.get(ConfigProperties.ELASTICURL));

            Assert.assertEquals(response.statusCode(), 201);

            response.prettyPrint();
        }
    }
}
