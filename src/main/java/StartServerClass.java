
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.BeforeClass;

public class StartServerClass {

    private static final String HOST = "localhost";
    private static final int Port = 8080;
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

    }
}
