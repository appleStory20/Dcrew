package g.sns_test.Post2.activity.post;

import java.util.List;

import g.sns_test.Post2.model.Note;


public interface PostView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);

}
