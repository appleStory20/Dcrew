package g.sns_test.Post_test;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter{

    private PostView view;

    public PostPresenter(PostView view) {
        this.view = view;
    }

    // void getData() {
    public void getData() {
        view.showLoading();
        //Request to server

        PostInterface postInterface = PostClient.getPostClient().create(PostInterface.class);
        Call<List<PostItem>> call = postInterface.getNotes();
        call.enqueue(new Callback<List<PostItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostItem>> call, @NonNull Response<List<PostItem>> response) {
                view.hideLoading();;
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PostItem>> call, @NonNull Throwable t) {
                view.hideLoading();;
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });


    }
}
