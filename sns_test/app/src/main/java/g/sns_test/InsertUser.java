package g.sns_test;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//private class InsertUser extends AsyncTask<String, Void, String> {
public class InsertUser extends AsyncTask<String, Void, String>  {

    @Override
    protected String doInBackground(String... params) {

        //회원 정보
        String account = params[1];
        String password = params[2];
        String name = params[3];
        String nickname = params[4];
        String email = params[5];

        //접근하고자 하는 서버 url
        String serverURL = params[0];

       String postParameters = "account="+account+"&password="+password+"&name="+name+"&nickname="+nickname+"&email="+email;
     //   String postParameters = "account="+account+"&password="+password+"&name="+name+"&nickname="+nickname;

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

            InputStream inputStream;

            //http response코드
            int responseStatusCode = httpURLConnection.getResponseCode();
            //서버로부터 넘어오는 echo 값을 받기 위한 스트림 생성


            //통신에 성공한 경우
            if(responseStatusCode == httpURLConnection.HTTP_OK){
                //php페이지의 echo 값을 가져온다
                inputStream = httpURLConnection.getInputStream();
            }
            //통신에 실패한 경우
            else {
                //에러 메세지 저장
                inputStream = httpURLConnection.getErrorStream();
            }

            //서버에서 넘어오는 한글이 깨지지 않게 하기 위해서 reader로 감싸주고
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //속도를 올리기 위해서 bufferedreader로 감싼다.
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //서버로부터 반환된 데이터를 실제로 담을 StringBuilder
            StringBuilder sb = new StringBuilder();
            //sb에 값을 넣어주기 위해서 잠깐 보관하는 매개체
            String line = null;


            if((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            //읽어오기 종료
            bufferedReader.close();

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("insert user 에러", "InsertData: Error ", e);
            return null;
        }


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //통신이 잘 돼서 사용자 정보가 들어갔을 때
        if(result != null){
            Log.d("통신","통신 완료");
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
        //통신 실패했을 때
        else {
            Log.d("통신","통신 실패");
            //Toast.makeText(getApplicationContext(), "문제가 생겼습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

}

