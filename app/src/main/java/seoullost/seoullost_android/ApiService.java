package seoullost.seoullost_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/user/login")
    Call<Token> login(@Body User user);

    @POST("/user/signup")
    Call<Void> signUp(@Body User user);

    @GET("/item/losts")
    Call<ItemResponse> getLostList(@Query("page") int page);
}
