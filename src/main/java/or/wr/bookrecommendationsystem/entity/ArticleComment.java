package or.wr.bookrecommendationsystem.entity;

public class ArticleComment {

    private int cmId;
    private String cmContent;
    private int aId;
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

    public int getaId() {
        return aId;
    }

    public void setbId(int aId) {
        this.aId = aId;
    }

    public String getAuthor() {
        return author;
    }

    public String getCmEditorDate() {
        return cmEditorDate;
    }


    public ArticleComment(int cmId, String cmContent, int aId, String author, String cmEditorDate) {
        this.cmId = cmId;
        this.cmContent = cmContent;
        this.aId = aId;
        this.author = author;
        this.cmEditorDate = cmEditorDate;
    }
    public ArticleComment(String cmContent, int aId, String author) {
        this.cmContent = cmContent;
        this.aId = aId;
        this.author = author;
    }
}
