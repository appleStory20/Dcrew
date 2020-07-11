package g.sns_test.Post2.activity.post;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import g.sns_test.Post2.activity.editor.PostEditActivity;
import g.sns_test.Post2.model.Note;
import g.sns_test.R;

public class PostActivity extends AppCompatActivity implements PostView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;

    FloatingActionButton fab;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    PostPresenter presenter;
    PostAdapter adapter;
    PostAdapter.ItemCLickListener itemCLickListener; //?
    List<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_post);

        //툴바 ->액션바
        Toolbar toolbar = (Toolbar)findViewById(R.id.post_toolbar);
        setSupportActionBar(toolbar);


        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view -> {
            startActivityForResult(new Intent(PostActivity.this, PostEditActivity.class), INTENT_ADD);
        });

        presenter = new PostPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemCLickListener = ((view, position) -> {
            int id = note.get(position).getId();
            String title = note.get(position).getTitle();
            String notes = note.get(position).getNote();

            Intent intent = new Intent(this, PostEditActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title",title);
            intent.putExtra("note", notes);
            startActivityForResult(intent, INTENT_EDIT);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getData(); //Reload data
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData(); //Reload dat
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        adapter = new PostAdapter(this, notes, itemCLickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        note = notes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
