package g.sns_test;

//모두의 게시판 글 생성 클래스
public class PostItem {
    private int postId;
    private String text;
    private String comment;
    private String date;
    private String nickName;
    private int like;

    //public PostItem(String text, String comment, String date, String nickname, int like)
    public PostItem(String text, String comment, String date)
    {
        this.text = text;
        this.comment = comment;
        this.date = date;
      //  this.nickName = nickname;
       // this.like = like;
    }
    public int getPostId() {return postId;}

    public String getText()
    {
        return text;
    }

    public String getComment()
    {
        return comment;
    }

    public String getDate()
    {
        return date;
    }

    public String getNick() { return nickName;}

    public int getLike() {return like;}
}
