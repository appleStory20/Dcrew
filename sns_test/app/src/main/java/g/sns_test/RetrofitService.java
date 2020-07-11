package g.sns_test;

import g.sns_test.Post_test.UploadResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    String baseURL = "http://203.237.142.229/";

    //게시물 업로드
    @Multipart
    @POST("postInsert.php")
    Call<UploadResponse> uploadResponse(
            @Part("postNum") RequestBody postNum,
            @Part("account") RequestBody account,
            @Part("nickname") RequestBody nickname,
            @Part("title") RequestBody title,
            @Part("contents") RequestBody contents
                );


/*
      @Multipart
    @POST("postInsert.php")
    Call<UploadResponse> uploadResponse(
            @Part("postNum") RequestBody postNum,
            @Part("account") RequestBody account,
            @Part("title") RequestBody title,
            @Part("contents") RequestBody contents,
            @Part("nickname") RequestBody nickname
    );
*/



    //게시물 수정
    @Multipart
    @POST("editpost.php")
    Call<UploadResponse> editResponse(
            @Part("postNum") RequestBody postNum,
            @Part("account") RequestBody account,
            @Part("title") RequestBody title,
            @Part("contents") RequestBody contents

    );

}
