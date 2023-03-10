package api.util;

import api.reqres_in.pojo_response.userdata_response.UserdataResponse;

public class RequestApiService {
    public RequestApiService getSingleUser(String id){
        UserdataResponse user = RequestSpecs.request().get("/api/users/" + id).then().statusCode(200).extract().as(UserdataResponse.class);
        user.getEmail();
        //UserModel.class UserJson.class
        return this;
    }
}
