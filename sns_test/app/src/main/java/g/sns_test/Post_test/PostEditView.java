package g.sns_test.Post_test;

public interface PostEditView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message) ;

}

