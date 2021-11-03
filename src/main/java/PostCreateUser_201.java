import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PostCreateUser_201 {


    public static void CreateUser_201_status(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(201);
        mokCreateUserResponce.withStatusMessage("Ok");
        mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withHeader("token","55555nkvsKGB");
        mokCreateUserResponce.withTransformerParameter("p","2YtL7jZaDzcsRN1w9eOlbon");
        mokCreateUserResponce.withBody("{\n" +
                "  \"ID\": \"525\",\n" +
                "  \"Points\" : \"825\",\n" +
                "  \"UserName\": \"Mikle\",\n" +
                "  \"Role\": \"User\",\n" +
                "  \"Age\": 25\n" +
                "}");
        WireMock.stubFor(WireMock.post("/users").willReturn(mokCreateUserResponce));
    }

    public static void CreateUser_201_statusJson(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(201);
        mokCreateUserResponce.withStatusMessage("Ok");
       // mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withHeader("token","55555nkvsKGB");
        mokCreateUserResponce.withTransformerParameter("p","2YtL7jZaDzcsRN1w9eOlbon");
        mokCreateUserResponce.withBodyFile("json/GetUser200.json");
        WireMock.stubFor(WireMock.post("/users").willReturn(mokCreateUserResponce));
    }
}
