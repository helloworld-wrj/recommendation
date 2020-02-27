package or.wr.bookrecommendationsystem.entity;

import java.io.Serializable;

public class Article implements Serializable {

    private int aId;
    private String title;
    private int cId;
    private String contentPath;
    private String coverPhotoPath;
    private String coverPhotoUrl;
    private String digest;
    private String editorDate;
    private String cValue;
    private String identification;
    private String bIds;


    public int getaId() {
        return aId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }


    public void setEditorDate(String editorDate) {
        this.editorDate = editorDate;
    }

    public String getEditorDate() {
        return editorDate;
    }

    public String getcValue() {
        return cValue;
    }

    public void setcValue(String cValue) {
        this.cValue = cValue;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", title='" + title + '\'' +
                ", cId=" + cId +
                ", contentPath='" + contentPath + '\'' +
                ", coverPhotoPath='" + coverPhotoPath + '\'' +
                ", coverPhotoUrl='" + coverPhotoUrl + '\'' +
                ", digest='" + digest + '\'' +
                ", editorDate='" + editorDate + '\'' +
                ", cValue='" + cValue + '\'' +
                ", identification='" + identification + '\'' +
                ", bIds='" + bIds + '\'' +
                '}';
    }

    //1、构造需要从前台获取的属性值
    public Article(String title, int cId, String contentPath, String coverPhotoPath, String coverPhotoUrl, String digest, String identification) {
        this.title = title;
        this.cId = cId;
        this.contentPath = contentPath;
        this.coverPhotoPath = coverPhotoPath;
        this.coverPhotoUrl = coverPhotoUrl;
        this.digest = digest;
        this.identification = identification;
    }

    //2、用于全文浏览页面时，第一次发送到浏览全文页面
    public Article(int aId, String title) {
        this.aId = aId;
        this.title = title;
    }

    //4、查询时所用

    public Article(int aId,String title, String cValue, String digest) {
        this.aId = aId;
        this.title = title;
        this.cValue = cValue;
        this.digest = digest;
    }

    public Article(int aId,String title, String cValue, String coverPhotoUrl, String digest) {
        this.aId = aId;
        this.title = title;
        this.cValue = cValue;
        this.coverPhotoUrl = coverPhotoUrl;
        this.digest = digest;
    }


    //4、更新时所用
    public Article(int aId,String title, int cId, String coverPhotoPath, String coverPhotoUrl, String digest) {
        this.aId = aId;
        this.title = title;
        this.cId = cId;
        this.coverPhotoPath = coverPhotoPath;
        this.coverPhotoUrl = coverPhotoUrl;
        this.digest = digest;
    }

    public Article(int aId, String title, int cId, String contentPath, String coverPhotoPath, String coverPhotoUrl, String digest, String editorDate, String cValue, String identification, String bIds) {
        this.aId = aId;
        this.title = title;
        this.cId = cId;
        this.contentPath = contentPath;
        this.coverPhotoPath = coverPhotoPath;
        this.coverPhotoUrl = coverPhotoUrl;
        this.digest = digest;
        this.editorDate = editorDate;
        this.cValue = cValue;
        this.identification = identification;
        this.bIds = bIds;
    }
    public Article(int aId, String title, int cId, String contentPath, String coverPhotoPath, String coverPhotoUrl, String digest, String editorDate, String cValue, String identification) {
        this.aId = aId;
        this.title = title;
        this.cId = cId;
        this.contentPath = contentPath;
        this.coverPhotoPath = coverPhotoPath;
        this.coverPhotoUrl = coverPhotoUrl;
        this.digest = digest;
        this.editorDate = editorDate;
        this.cValue = cValue;
        this.identification = identification;
    }
}
