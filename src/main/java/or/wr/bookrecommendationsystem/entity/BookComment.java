package or.wr.bookrecommendationsystem.entity;

public class BookComment {
    private int cmId;
    private String cmContent;
    private int bId;
    private String author;
    private String cmEditorDate;

    public int getCmId() {
        return cmId;
    }

    public String getCmContent() {
        return cmContent;
    }

    public void setCmContent(String cmContent) {
        this.cmContent = cmContent;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getAuthor() {
        return author;
    }

    public String getCmEditorDate() {
        return cmEditorDate;
    }


    public BookComment(int cmId, String cmContent, int bId, String author, String cmEditorDate) {
        this.cmId = cmId;
        this.cmContent = cmContent;
        this.bId = bId;
        this.author = author;
        this.cmEditorDate = cmEditorDate;
    }
    public BookComment(String cmContent, int bId, String author) {
        this.cmContent = cmContent;
        this.bId = bId;
        this.author = author;
    }


}
