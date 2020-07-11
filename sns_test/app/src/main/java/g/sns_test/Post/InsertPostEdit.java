package g.sns_test.Post;

/*
public class InsertPostEdit extends AppCompatActivity {

    private static String IP_ADDRESS = "203.237.142.229";
    private static String TAG = "phpexample";

    private EditText mtitleEditText;
    private EditText mcontentsyEditText;
    private ArrayList<PostData> mArrayList;

    //게시물 번호
    public static int postNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post_edit);

        mtitleEditText = (EditText)findViewById(R.id.titleEditText);
        mcontentsyEditText= (EditText)findViewById(R.id.contentsEditText);

        mArrayList = new ArrayList<>();

        // 게시글의 번호
        Intent intent = getIntent();
        postNum = intent.getIntExtra("postNum",0);
        Log.d("게시물 번호", String.valueOf(postNum));


        Button buttonInsert = (Button)findViewById(R.id.button);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title =  mtitleEditText.getText().toString();
                String contents =  mcontentsyEditText.getText().toString();


//                InsertPostEdit.InsertData task = new InsertPostEdit.InsertData();
//                task.execute("http://" + IP_ADDRESS + "/postInsert.php", title, contents);
                upload(title, contents);

//                mtitleEditText.setText("");
//                mcontentsyEditText.setText("");

            }
        });
    }
/*

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(InsertPostEdit.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            //게시글 정보
            //String title = (String)params[1];
            String title = params[1];
            //String contents = (String)params[2];
            String contents = params[2];

            //접근하고자 하는 서버 url
            //String serverURL = (String)params[0];
            String serverURL = params[0];
            String postParameters = "title=" + title + "&contents=" + contents;

            try {
                //url객체 생성
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");

                //서버와 연결
                httpURLConnection.connect();

                //서버로 데이터를 내보내줄 스트림 생성
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //속도 향상을 위해서 buffer스트림 사용
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                //스트림을 통해서 서버로 데이터를 바이트단위로 전송
                bufferedOutputStream.write(postParameters.getBytes("UTF-8"));
                //스트림의 데이터를 비워주고
                bufferedOutputStream.flush();
                //스트림을 종료시켜준다.(이 과정을 해줘야 실제로 데이터가 넘어감)
                bufferedOutputStream.close();

                //http response코드
                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                //통신에 성공한 경우
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                //통신에 실패한 경우
                else{
                    //에러 메세지 저장
                    inputStream = httpURLConnection.getErrorStream();
                }

                //서버에서 넘어오는 한글이 깨지지 않게 하기 위해서 reader로 감싸주고
                //서버로 데이터를 내보내기 위한 스트림 객체 선언
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                //전송할 데이터를 바이트 어레이로 풀어서 전달
                //속도를 올리기 위해서 bufferedreader로 감싼다.
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //서버로부터 반환된 데이터를 실제로 담을 StringBuilder
                StringBuilder sb = new StringBuilder();
                //sb에 값을 넣어주기 위해서 잠깐 보관하는 매개체
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                //읽어오기 종료
                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }
        }



        //게시물 생성 버튼을 누를 때 서버로 데이터를 넘겨주는 메소드
        private void upload(
                String title,
                String contents) {

            //서버와 통신이 이루어질 동안 보일 로딩 창
            ProgressDialog progressDialog = new ProgressDialog(InsertPostEdit.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show(InsertPostEdit.this, "게시물 생성", "잠시만 기다려주세요.", true, false);


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //레트로핏 세팅
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://203.237.142.229/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //Log.d("아이디", LoginUser.getAccount());

            String nick =  LoginUser.getNickname();
            Log.d("닉네임: ", nick);

            //서버로 보내줄 데이터 param 설정
            RequestBody postNumPart = RequestBody.create(MultipartBody.FORM, String.valueOf(postNum));
            RequestBody accountPart = RequestBody.create(MultipartBody.FORM, LoginUser.getAccount());
            RequestBody NicknamePart = RequestBody.create(MultipartBody.FORM, nick);
            RequestBody titlePart =  RequestBody.create(MultipartBody.FORM, title);
            RequestBody contentsPart =  RequestBody.create(MultipartBody.FORM, contents);



            //레트로핏 인터페이스 설정
            RetrofitService retrofitService = retrofit.create(RetrofitService.class);

            Call<UploadResponse> call = retrofitService.uploadResponse(postNumPart, accountPart, NicknamePart,  titlePart, contentsPart);

            //Call<UploadResponse> call = retrofitService.uploadResponse(accountPart, titlePart, contentsPart);

            call.enqueue(new Callback<UploadResponse>() {
                @Override
                public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {

                    Glide.get(getApplicationContext()).clearMemory();

                    //작업이 완료되면 로딩 다이얼로그를 없애주고 메인화면으로 인텐트
                    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "생성 완료", Toast.LENGTH_SHORT).show();

                    //메인 액티비티로 데이터를 함께 넘겨준다.
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                @Override
                public void onFailure(Call<UploadResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    //Toast.makeText(getApplicationContext(), "생성 완료", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT).show();
                    Log.d("업로드 실패 에러: ", t.getMessage());

                    //메인 액티비티로 데이터를 함께 넘겨준다.
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
    */