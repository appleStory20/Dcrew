package g.sns_test.Post2.api;

import java.util.List;

import g.sns_test.Post2.model.Note;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/note_php/save.php")
    Call<Note> saveNote(
            @Field("title") String title,
            @Field("note") String note
    );

    @GET("/note_php/notes.php")
    Call<List<Note>> getNotes();

    @FormUrlEncoded
    @POST("/note_php/update.php")
    Call<Note> updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note
    );

    @FormUrlEncoded
    @POST("/note_php/delete.php")
    Call<Note> deleteNote(
            @Field("id") int id
    );
}
