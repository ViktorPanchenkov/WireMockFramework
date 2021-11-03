import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class DeleteUser404 {
    public static void createResponceDefForDeleteUser404(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(404);
        mokCreateUserResponce.withStatusMessage("Not found");
        mokCreateUserResponce.withHeader("content-type","application/json");
        // mokCreateUserResponce.withTransformerParameter("id","569394ppp45tt");
        mokCreateUserResponce.withBody("User does not exist");
        WireMock.stubFor(WireMock.delete("/users/105").willReturn(mokCreateUserResponce));
    }
}
