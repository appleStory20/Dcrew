package g.sns_test;

public class LoginUser {//로그인한 사용자 정보

    private static String account;
    private static String nickname;
    //singleton instance
    private static LoginUser loginUser;

    private LoginUser() {

    }

    private LoginUser(String account, String nickname) {
        this.account = account;
        this.nickname = nickname;
    }

    public static LoginUser getInstance() {
        return loginUser;
    }

    public static LoginUser initInstance(String account, String nickname){
        loginUser = new LoginUser(account, nickname);
        return loginUser;
    }



    public static String getAccount() {
        return account;
    }

    public static String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname){
        this.nickname = nickname;
    }


}
