package g.sns_test.Post2.activity.editor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import g.sns_test.R;

public class PostEditActivity extends AppCompatActivity implements PostEditView {

    EditText et_title, et_note;
    ProgressDialog progressDialog;

    PostEditPresenter presenter;

    int  id;

    String title, note;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_editor);

        //툴바 ->액션바
        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);

        // Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new PostEditPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        title = intent.getStringExtra("title");
        note = intent.getStringExtra("note");

        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if(id != 0){
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = et_title.getText().toString().trim();
        String note = et_note.getText().toString().trim();

        switch (item.getItemId()){
            case R.id.save:
                // Save
                if(title.isEmpty()){
                    et_title.setError("제목을 입력해주세요");
                } else if (note.isEmpty()){
                    et_note.setError("내용을 입력해주세요");
                } else {
                    presenter.saveNote(title, note);
                }
                return true;
            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;
            case R.id.update:
                if(title.isEmpty()){
                    et_title.setError("제목을 입력해주세요");
                } else if (note.isEmpty()){
                    et_note.setError("내용을 입력해주세요");
                } else {
                    presenter.updateNote(id, title, note);
                }
                return true;
            case R.id.delete:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("삭제");
                alertDialog.setMessage("정말 게시글을 삭제할까요?");
                alertDialog.setNegativeButton("Yes",  (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteNote(id);
                });
                alertDialog.setPositiveButton("No", (dialog, which) -> dialog.dismiss());

                alertDialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(PostEditActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); // Kembali ke main activity
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(PostEditActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if(id != 0){
            et_title.setText(title);
            et_note.setText(note);

            getSupportActionBar().setTitle("Update Note");
            readMode();
        } else {
            // Default Color Setup
            editMode();
        }
    }

    private void editMode() {
        et_title.setFocusableInTouchMode(true);
        et_note.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_title.setFocusableInTouchMode(false);
        et_note.setFocusableInTouchMode(false);
        et_title.setFocusable(false);
        et_note.setFocusable(false);
    }
}
