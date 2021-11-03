import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;


public class Tests {
    private static final String HOST = "localhost";
    private static final int Port = 8080;
    private static WireMockServer server = new WireMockServer(Port);

    public void createStub() {
        stubFor(get(urlEqualTo("/api/services"))
                .willReturn(aResponse().withStatus(200)
                        .withBody("This is sample responce")
                        .withHeader("Content-Type", "application/json")));

    }

    @Test
    public void verifyStab() {
        server.start();
        WireMock.configureFor(HOST, Port);
        createStub();

        RestAssured.baseURI = "http://localhost:8080";

        Response response = given()
                .log()
                .body()
                .get("/api/services");

        assertThat(response).isNotNull();
        WireMock.shutdownServer();

    }
}
