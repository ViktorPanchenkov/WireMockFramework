import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PostUpdateUser200 {


    public static void UpdateUser_200_status(){
        ResponseDefinitionBuilder mokCreateUserResponce = new ResponseDefinitionBuilder();
        mokCreateUserResponce.withStatus(200);
        mokCreateUserResponce.withStatusMessage("Ok");
        mokCreateUserResponce.withHeader("content-type","application/json");
        mokCreateUserResponce.withHeader("token","55555nkvsKGB");
        mokCreateUserResponce.withTransformerParameter("p","2YtL7jZaDzcsRN1w9eOlbon");
        mokCreateUserResponce.withBody("{\n" +
                "  \"ID\": \"525\",\n" +
                "  \"Points\" : \"1000\",\n" +
                "  \"UserName\": \"Andrei\",\n" +
                "  \"Role\": \"Owner\",\n" +
                "  \"Age\": 28\n" +
                "}");
        WireMock.stubFor(WireMock.post("/users/525").willReturn(mokCreateUserResponce));
    }
}
