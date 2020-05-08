package g.sns_test;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import java.net.PasswordAuthentication;

public class sendEmail {

    //네이버로 인증 메일을 보내기 위한 메소드
    //param: 회원가입시 인증번호를 받기 위해 입력한 이메일
    //이 메소드는 smtp를 통해서 네트워크 통신을 하기 때문에 메인 스레드에서 바로 호출하면 android.os.NetworkOnMainThreadException에러가 뜬다.
    //반드시 asynctask와 같은 클래스에 넣어서 실행되도록 하자.
    public void sendEmail(String email, String verificationNum) {
        //메일을 전송하는 호스트(네이버)
        String host = "smtp.naver.com";
        //String host = "smtp.gmail.com";
        //메일을 전송하는 수신자 아이디
        final String sender = "dcrew_developer@naver.com";
        //final String sender = "dcrew.developers@gmail.com";
        //메일을 전송하는 수신자의 비밀번호
        //final String password = "rla933466r";
        final String password = "ncs213!@";


        //smtp서버 설정
        Properties properties = new Properties();
        //메일을 전송하는 호스트 설정
        properties.put("mail.smtp.host", host);
        //메일을 전송할 포트(구글의 경우 465, 네이버 587)
        properties.put("mail.smtp.port", 465);
        //properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
        // properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            //인증메일을 받을 주소 추가
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            //이메일의 제목 설정
            message.setSubject("SNS회원가입 인증번호");

            //메일의 내용 입력
            message.setText("인증번호: " + verificationNum);

            //이메일 전송
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

/*
    //처음에 메일 전송 메소드를 메인 스레드에서 실행했을 때 android.os.NetworkOnMainThreadException라는 에러가 떴다.
    //해당 에러는 네트워크 통신을 메인 스레드에서 실행했을 때 발생하는 문제다.
    //네트워크 통신은 메인 스레드가 아닌 서브 스레드에서 실행시켜야 한다는 것을 명심하자.
    //해당 클래스는 메일 전송을 서브스레드에서 실행시키기 위한 asynctask 클래스다.
    private class SendMailThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //사용자가 입력한 email
            String email = params[0];

            //본인인증 번호
            String verificationNum = params[1];
            sendEmail(email, verificationNum);
            return null;
        }
    }

 */

    //인증번호를 위한 난수를 생성하는 메소드
    //private String randomNum() {
    protected String randomNum() {
        //인증번호의 자리수
        int length = 6;
        String number = "";

        Random random = new Random();

        for (int i = 0; i < length; i++) {

            //0-9사이의 난수를 담는 변수
            String ran = Integer.toString(random.nextInt(10));

            //인증번호에 중복되는 난수가 없는 경우
            if (!number.contains(ran)) {
                //인증번호에 난수를 추가
                number += ran;
            }
            //인증번호에 중복되는 난수가 있느면
            else {
                //반복문을 이전 단계로 돌려서 다시 실행
                i -= 1;

            }

        }
        //생성된 인증번호 리턴
        return number;
    }

}
