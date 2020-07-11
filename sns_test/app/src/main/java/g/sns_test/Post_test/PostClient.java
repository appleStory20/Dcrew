package g.sns_test.Post_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {

    private static final String BASE_URL = "http://203.237.142.229/";
    private static Retrofit retrofit;

     public static Retrofit getPostClient() {

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            return retrofit;
        }

}