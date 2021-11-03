import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetUserRequestEmptyBody {


    public static void getUserEmptyResponce(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(200);
        mokCreateUserResponce.withStatusMessage("Not found");
        mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withHeader("token","6666gsdfsdf");
        mokCreateUserResponce.withBody("");

        WireMock.stubFor(WireMock.get("/users/2").willReturn(mokCreateUserResponce));
    }
}
