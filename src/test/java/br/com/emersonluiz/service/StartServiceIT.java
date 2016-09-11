package br.com.emersonluiz.service;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.util.Assert;

import br.com.emersonluiz.model.Person;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class StartServiceIT extends TestParent {

    private static final String url = "/start-rest-assured/";

    @Test
    public void testHelloWorld() {
        String response = expect().log().all().statusCode(200)
                          .contentType(ContentType.TEXT).when().get(url + "helloworld")
                          .asString();

        assertEquals("Hello World", response);
    }

    @Test
    public void testGetPerson() {
        String uuid = "f0f1a959-b3c3-4126-b16e-ea50b5b1dc20";

        expect().log().all().statusCode(200).contentType(ContentType.JSON)
        .when().body("containsKey('id')", is(true))
        .body("id", equalTo(uuid))
        .body("containsKey('name')", is(true))
        .body("containsKey('age')", is(true))
        .get(url + "/person/" + uuid);
    }

    @Test
    public void testListPerson() {
        expect().log().all().statusCode(200).contentType(ContentType.JSON)
        .when().body("name", hasItems("Clark Kent", "Bruce Wayne"))
        .get(url + "person-list");
    }

    @Test
    public void testCreatePerson() {
        Person person = new Person();
        person.setName("Robert Bruce Banner");
        person.setAge(45);

        Response response = given().log().all().content(person).with()
                            .contentType(ContentType.JSON).then().expect().log().all()
                            .statusCode(201).contentType(ContentType.JSON).when()
                            .body("id", notNullValue())
                            .body("name", equalTo(person.getName()))
                            .body("age", equalTo(person.getAge()))
                            .post(url + "person-create");

        String location = response.getHeader("Location");
        String[] paths = location.split("/");
        String id = paths[paths.length - 1];

        Assert.notNull(id);
    }
}
