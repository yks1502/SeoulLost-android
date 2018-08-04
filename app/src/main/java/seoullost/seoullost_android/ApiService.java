package seoullost.seoullost_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/user/login")
    Call<Token> login(@Body User user);

    @POST("/user/signup")
    Call<Void> signUp(@Body User user);
}
