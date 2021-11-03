import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class DeleteUser_200_Status {


    public static void CreateResponceDefForDeleteUser_200(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(200);
        mokCreateUserResponce.withStatusMessage("Ok");
        mokCreateUserResponce.withHeader("content-type","application/json");
       // mokCreateUserResponce.withTransformerParameter("id","569394ppp45tt");
        mokCreateUserResponce.withBody("{\n" +
                "  \"Status\": \"User deleted!\"\n" +
                "}");
        WireMock.stubFor(WireMock.delete("/users/79").willReturn(mokCreateUserResponce));
    }
}
