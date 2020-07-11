package g.sns_test.Post;

/*
public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view){
        this.view = view;
    }

    void savePostData(final String title, final String contents){
        view.showProgress();

        ApliInterface apiInterface = ApiClient,getApliClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.savePostData(title, contents);

        call.enqueue(new Callback<Note>()){
            @Override
                    public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> res){
                        progressDialog.dismiss();

                        if(response.isSuccessful() && response.body() != null){
                            Boolean success = response.body().getSuccess();
                            if (success) {
                                view.onAddSuccess(response.body().getMessage());
                            }
                            else{
                                view.onAddError(response.body().getMessage);
                            }
                        }
            }
            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t){
                view.hideProgress();






            }
        }
    }
}
*/