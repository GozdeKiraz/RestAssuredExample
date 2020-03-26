import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;



public class Request {

    @Before

    public void setUp(){
        RestAssured.baseURI="http://localhost:3000/";
    }
    public static Integer getRequest(){


        Response response=
        given()
                .log().all()
                .when()
                .get("/users/").prettyPeek()
                .then()
                .statusCode(200).extract().response();
     //int userId=Integer.parseInt(response.jsonPath().getString("[2].id"));
        int userIdSıze=Integer.parseInt(response.jsonPath().getString("id.size()"));
     //System.out.println(userId);
        return userIdSıze;
    }
    public static void postRequest(int userId){
        Response response=
                given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"id\": "+userId+" ,\n" +
                        "    \"first_name\": \"Roger\",\n" +
                        "    \"last_name\": \"Bacon\",\n" +
                        "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                        "}")
                        .log().all()
                        .when()
                        .post("/users/").prettyPeek()
                        .then()
                        .statusCode(201).extract().response();
    }
    public static void putRequest(){

        List<String> eleman=new ArrayList<String>();
        eleman.add("1");
        eleman.add("2");
        eleman.add("3");
        eleman.add("4");
        eleman.add("5");
        Random random=new Random();
        int rastgeleId=random.nextInt(eleman.size());


        Response response=
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"id\": "+rastgeleId+" ,\n" +
                                "    \"first_name\": \"Joe\",\n" +
                                "    \"last_name\": \"Bacon\",\n" +
                                "    \"email\": \"rogerbacon12@yahoo.com\"\n" +
                                "}")
                        .log().all()
                        .when()
                        .put("/users/"+rastgeleId+"/").prettyPeek()
                        .then()
                        .statusCode(200).extract().response();
    }

    public void deleteRequest(){

        List<String> eleman=new ArrayList<String>();
        eleman.add("1");
        eleman.add("2");
        eleman.add("3");
        eleman.add("4");
        eleman.add("5");

        Random random=new Random();
        int rastId=random.nextInt(eleman.size());

        Response response=
                given()
                        .log().all()
                        .when()
                        .delete("/users/"+rastId+"/").prettyPeek()
                        .then()
                        .statusCode(200).extract().response();
    }


    @Test
    public void requestScenario1() {

        int userId= getRequest() +5;
        postRequest(userId);
    }



    @Test
    public void requestScenario2(){

      putRequest();
      getRequest();



    }

    @Test
    public void requestScenario3(){

        deleteRequest();



    }


}

