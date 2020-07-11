package g.sns_test.Post2.activity.editor;

public interface PostEditView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message) ;

}

