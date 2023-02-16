package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

public class RESTtest {

    /**
     * 5.3. Тестирование связи взаимодействия компонентов (REST API):
     * создать HTTP-запрос, отправить его REST API,
     * проверить, что полученный ответ соответствует ожидаемому.
     * */
    @Test
    void shouldTestSomeData() {
                given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Good Day")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Good Day"))
        ;
    }

    /**
     * 5.4. Тестирование связи взаимодействия компонентов (REST API):
     * создать HTTP-запрос, отправить его REST API,
     * проверить, что полученный ответ соответствует ожидаемому.
     * */
    @Test
    void shouldTestPost() {
        given()
                .baseUri("https://postman-echo.com")
                .body("handWave")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("handWave"))

        ;
    }

    /**
     * 5.5. Тестирование связи взаимодействия компонентов (REST API):
     * создать HTTP-запрос, отправить его REST API,
     * проверить, что полученный ответ соответствует ожидаемому.
     * */
    @Test
    void shouldRequestBasicAuth() {
        given()
                .baseUri("https://postman-echo.com")
                .headers("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==")
                .when()
                .get("/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
        ;

    }

}