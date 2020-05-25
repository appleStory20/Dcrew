package g.sns_test;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertPostActivity extends AppCompatActivity {

    private EditText fieldTitle, fieldBody;
    private TextView TextFieldTitle, TextFieldBody;
    private Button inserPostButton;

    public static String IP_ADDRESS = "203.237.142.229";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post);

        // 포스트 글 담는 변수
        fieldTitle = findViewById(R.id.fieldTitle);
        fieldBody = findViewById(R.id.fieldBody);

        //글 작성 버튼
        inserPostButton = findViewById(R.id.insertButton);

        fieldTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //택스트가 변할때마다 호출되는 메소드다.
            @Override
            public void afterTextChanged(Editable s) {
                // 제목 입력되는 값
                String Title = fieldTitle.getText().toString();

                //제목이 공백인 경우
                if(TextUtils.isEmpty(Title)){
                    Toast.makeText(getApplicationContext(), "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                }
            }
        });



    }
}
