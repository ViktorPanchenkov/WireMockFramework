import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetUserRequest_404_status {


    public static void GetUser_404(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(404);
        mokCreateUserResponce.withStatusMessage("Not found");
        mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withBody("User does not exist");

        WireMock.stubFor(WireMock.get("/users/2").willReturn(mokCreateUserResponce));
    }
}
