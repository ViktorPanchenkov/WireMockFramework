import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.hasKey;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MockTests {

    public static final String HOST = "localhost";
    public static final int Port = 8080;
    private static WireMockServer server = new WireMockServer(Port);

    @BeforeMethod
    public void SetUPServer(){
        server.start();
        WireMock.configureFor(HOST,Port);

    }

    @Test
    public void CheckGetUserResponce_200(){
        GetUserRequest_200_status.CreateResponceDefForGetUser();

        String testApi = "http://localhost:" + StartServerClass.Port + "/users/1";
        System.out.println("Servere to be hit: " + testApi);

        Response response = given().
                        contentType(ContentType.JSON).
                        get("http://localhost:8080/users/1")

                .then().statusCode(200)
                .log().all()
                .assertThat().body("ID",equalTo(10))
                .assertThat().body("UserName",equalTo("Kolia"))
                .assertThat().body("Role",equalTo("Manager"))
                .extract().response();
        assertThat(response.getHeader("token"),equalTo("55555nkvsKGB"));

    }
    @Test
    public void CheckUserResponce_404(){
        GetUserRequest_404_status.GetUser_404();
        String testApi = "http://localhost:" + Port + "/users/2";
        System.out.println("Servere to be hit: " + testApi);

        Response response = given().
                        contentType(ContentType.JSON).
                        get(testApi)

                .then().log().all().
                        statusCode(200)
                .assertThat().body("ID",equalTo(15))
                .assertThat().body("UserName",equalTo("Ivan"))
                .assertThat().body("Role",equalTo("Slave"))
                .extract().response();
        assertThat(response.getHeader("token"),equalTo("6666gsdfsdf"));


    }
    @Test
    public void CreateUser_201(){
        PostCreateUser_201.CreateUser_201_status();
        String testApi = "http://localhost:" + Port + "/users";
        Response response =  RestAssured.
                given().
                        contentType(ContentType.JSON).
                        body("json/PostUserJson.json").
                        post(testApi)
                .then().log().all().
                        statusCode(201)
                .body("$", hasKey("ID"))
                .body("$",hasKey("Points"))
                .body("$", hasKey("Role"))
                .extract().response();
    }

    @Test
    public void UpdateUser(){
       PostUpdateUser_200.UpdateUser_200_status();

        String testApi = "http://localhost:" + Port + "/users/525";
        Response response =  RestAssured.
                given().
                contentType(ContentType.JSON).
                body("json/UpdateUser.json").
                post(testApi)
                .then().log().all().
                statusCode(200)
                .assertThat().body("Role",equalTo("Owner"))
                .assertThat().body("UserName", equalTo("Andrei"))
                .assertThat().body("Age",equalTo(28))
                .extract().response();

    }
    @Test
    public void DeleteUser_200(){
      DeleteUser_200_Status.CreateResponceDefForDeleteUser_200();

        String testApi = "http://localhost:" + Port + "/users/79";
        Response response = given().
                contentType(ContentType.JSON).
                delete(testApi)
                .then().log().all().
                        statusCode(200)
                .assertThat().body("Status", equalTo("User deleted!"))
                .extract().response();

    }
    @Test
    public void DeleteUser404(){
           DeleteUser404.CreateResponceDefForDeleteUser_404();

        String testApi = "http://localhost:" + Port + "/users/105";
        Response response = given().
                contentType(ContentType.JSON).
                delete(testApi)
                .then().log().all().
                        statusCode(200)
                .assertThat().body("Status", equalTo("User deleted!"))
                .extract().response();

    }
    @AfterMethod
    public void closeServer(){
        if(server.isRunning() && null != server){
            System.out.println("Shutdown");
            server.shutdown();
        }
    }
}
