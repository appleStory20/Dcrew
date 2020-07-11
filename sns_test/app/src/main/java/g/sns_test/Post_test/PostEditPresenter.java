package g.sns_test.Post_test;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEditPresenter {
    private PostEditView view;

    public PostEditPresenter(PostEditView view){
        this.view = view;
    }

    void savePost(final String title, final String note){
        view.showProgress();

        PostInterface postInterface = PostClient.getPostClient().create(PostInterface.class);
        Call<PostItem> call = postInterface.savePost(title, note);

        call.enqueue(new Callback<PostItem>() {
            @Override
            public void onResponse(@NonNull Call<PostItem> call, @NonNull Response<PostItem> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostItem> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void updatePost(int id, String title, String note) {
        view.showProgress();;
        PostInterface postInterface = PostClient.getPostClient().create(PostInterface.class);

        Call<PostItem> call = postInterface.updatePost(id, title, note);
        call.enqueue(new Callback<PostItem>() {
            @Override
            public void onResponse(@NonNull Call<PostItem> call, @NonNull Response<PostItem>  response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else{
                        view.onRequestSuccess(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostItem> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deletePost(int id){
        view.showProgress();
        PostInterface postInterface = PostClient.getPostClient().create(PostInterface.class);
        Call<PostItem> call = postInterface.deletePost(id);
        call.enqueue(new Callback<PostItem>() {
            @Override
            public void onResponse(@NonNull Call<PostItem> call, @NonNull Response<PostItem> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else{
                        view.onRequestSuccess(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostItem> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}

