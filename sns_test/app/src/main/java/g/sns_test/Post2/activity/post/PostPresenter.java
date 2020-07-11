package g.sns_test.Post2.activity.post;

import androidx.annotation.NonNull;

import java.util.List;

import g.sns_test.Post2.api.ApiClient;
import g.sns_test.Post2.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import g.sns_test.Post2.model.Note;

public class PostPresenter {

    private PostView view;

    public PostPresenter(PostView view) {
        this.view = view;
    }

    void getData(){
        view.showLoading();
        //Request to server
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note>> call,@NonNull Response<List<Note>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Note>> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
