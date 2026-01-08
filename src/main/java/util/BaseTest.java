package util;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    @Before
    public void setupRestAssured() {
        RestAssured.baseURI = BASE_URL;
    }
}
