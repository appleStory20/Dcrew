package g.sns_test.Post_test;

import java.util.List;

public interface PostView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<PostItem> notes);
    void onErrorLoading(String message);

}
