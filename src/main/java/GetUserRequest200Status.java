import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetUserRequest200Status {


    public static void CreateResponseDefForGetUser(){
        ResponseDefinitionBuilder mokCreateUserResponse = new ResponseDefinitionBuilder();
        mokCreateUserResponse.withStatus(200);
        mokCreateUserResponse.withStatusMessage("Ok");
        mokCreateUserResponse.withHeader("content-type","application/json");
        mokCreateUserResponse.withHeader("token","55555nkvsKGB");
        mokCreateUserResponse.withBody("{\n" +
                "  \"ID\": 10,\n" +
                "  \"UserName\": \"Kolia\",\n" +
                "  \"Role\": \"Manager\",\n" +
                "  \"Age\": 25,\n" +
                "  \"Car\": \"Rols Roys\"\n" +
                "}");
        WireMock.stubFor(WireMock.get("/users/1").willReturn(mokCreateUserResponse));
    }

    public static void CreateResponceDefForGetUserJson(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(200);
        mokCreateUserResponce.withStatusMessage("Ok");
        mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withHeader("token","55555nkvsKGB");
        mokCreateUserResponce.withBodyFile("json/GetUser200.json");
        WireMock.stubFor(WireMock.get("/users/1").willReturn(mokCreateUserResponce));
    }




}
