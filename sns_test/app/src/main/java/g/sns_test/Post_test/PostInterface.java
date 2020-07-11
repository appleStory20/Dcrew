package g.sns_test.Post_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostInterface{

    @FormUrlEncoded
    @POST("postsave.php")
    Call<PostItem> savePost(
            @Field("title") String title,
            @Field("note") String note
    );

    @GET("notes.php")
    Call<List<PostItem>> getNotes();

    @FormUrlEncoded
    @POST("update.php")
    Call<PostItem> updatePost(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<PostItem> deletePost(@Field("id") int id);

}