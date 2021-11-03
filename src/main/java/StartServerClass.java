
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class StartServerClass {

    public static final String HOST = "localhost";
    public static final int Port = 8080;
    private static WireMockServer server = new WireMockServer(Port);

    @BeforeClass
    public void initalizeServer(){
        server.start();
        WireMock.configureFor(HOST,Port);

        ResponseDefinitionBuilder mockResponce = new ResponseDefinitionBuilder();
        mockResponce.withStatus(201);
        mockResponce.withStatusMessage("Hello World");
        mockResponce.withHeader("Content-Type","text/json");
       mockResponce.withHeader("token","1111");
        mockResponce.withHeader("Set-Cookie","session_id933514");
        mockResponce.withBody("text in the body");
        WireMock.stubFor(WireMock.get("/users/1").willReturn(mockResponce));

       // assertThat(mockResponce).isNotNull();



    }


    @Test
    public void DebugCode(){
        String testApi = "http://localhost:" + Port + "/users/1";
        System.out.println("Servere to be hit: " + testApi);

        Response response = RestAssured
                .given().
                        get("http://localhost:8080/users/1")
                .then().statusCode(201)
                .log().all()
                .extract().response();

        assertThat(response.getHeader("token"),equalTo("1111"));
        assertThat(response.getCookie("cookie"), equalTo("session_id933514"));



    }
    @AfterClass
    public void closeServer(){
        if(server.isRunning() && null != server){
            System.out.println("Shutdown");
            server.shutdown();
        }
    }
}
