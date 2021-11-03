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

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String URL = String.format("http://%s:%d/%s",HOST, PORT, "%s");
    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(PORT);

    @BeforeMethod
    public void setUPServer() {
        WIRE_MOCK_SERVER.start();
        WireMock.configureFor(HOST, PORT);

    }

    @Test
    public void checkGetUserResponse200() {
        GetUserRequest200Status.CreateResponseDefForGetUser();

        String testApi = String.format(URL,"users/1");
        System.out.println("Servere to be hit: " + testApi);

        Response response = given().
                contentType(ContentType.JSON).
                get(testApi)

                .then().statusCode(200)
                .log().all()
                .assertThat().body("ID", equalTo(10))
                .assertThat().body("UserName", equalTo("Kolia"))
                .assertThat().body("Role", equalTo("Manager"))
                .extract().response();
        assertThat(response.getHeader("token"), equalTo("55555nkvsKGB"));

    }

    @Test
    public void checkUserEmptyResponse() {
        GetUserRequestEmptyBody.getUserEmptyResponce();
        String testApi = String.format(URL,"users/2");
        System.out.println("Servere to be hit: " + testApi);

        Response response = given().
                contentType(ContentType.JSON).
                get(testApi)

                .then().log().all().
                statusCode(200)
                .assertThat().body(hasKey("ID"))
                .assertThat().body(hasKey("UserName"))
                .assertThat().body(hasKey("Role"))
                .extract().response();
        assertThat(response.getHeader("token"), equalTo("6666gsdfsdf"));


    }

    @Test
    public void createUser201() {
        PostCreateUser201.createUser201Status();
        String testApi = String.format(URL,"users/add");
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                body("json/PostUserJson.json").
                post(testApi)
                .then().log().all().
                statusCode(201)
                .body("$", hasKey("ID"))
                .body("$", hasKey("Points"))
                .body("$", hasKey("Role"))
                .extract().response();
    }

    @Test
    public void updateUser() {
        PostUpdateUser200.UpdateUser_200_status();

        String testApi = String.format(URL,"users/525");
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).
                body("json/UpdateUser.json").
                post(testApi)
                .then().log().all().
                statusCode(200)
                .assertThat().body("Role", equalTo("Owner"))
                .assertThat().body("UserName", equalTo("Andrei"))
                .assertThat().body("Age", equalTo(28))
                .extract().response();

    }

    @Test
    public void deleteUser200() {
        DeleteUser200Status.createResponseDefForDeleteUser200();

        String testApi = String.format(URL,"users/79");
        Response response = given().
                contentType(ContentType.JSON).
                delete(testApi)
                .then().log().all().
                statusCode(200)
                .assertThat().body("Status", equalTo("User deleted!"))
                .extract().response();

    }

    @Test
    public void deleteUser404() {
        DeleteUser404.createResponceDefForDeleteUser404();

        String testApi = String.format(URL,"users/105");
        Response response = given().
                contentType(ContentType.JSON).
                delete(testApi)
                .then().log().all().
                statusCode(200)
                .assertThat().body("Status", equalTo("User deleted!"))
                .extract().response();

    }

    @AfterMethod
    public void closeServer() {
        if (WIRE_MOCK_SERVER.isRunning()) {
            System.out.println("Shutdown");
            WIRE_MOCK_SERVER.shutdown();
        }
    }
}
