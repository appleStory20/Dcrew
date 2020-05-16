package g.sns_test;

//모두의 게시판 글 생성 클래스
public class PostItem {
    String text;
    String comment;
    String date;

    public PostItem(String text, String comment, String date)
    {
        this.text = text;
        this.comment = comment;
        this.date = date;
    }

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
}
