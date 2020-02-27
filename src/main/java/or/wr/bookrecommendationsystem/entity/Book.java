package or.wr.bookrecommendationsystem.entity;

import java.io.Serializable;

public class Book implements Serializable {

    private int bId;
    private String name;
    private String author;
    private int cId;
    private String publication;
    private String publicationDate;
    private String coverPhotoPath;
    private String coverPhotoUrl;
    private String digest;
    private String viewed;
    private String whoAdd;


    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
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

    public String getViewed() {
        return viewed;
    }

    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    public String getWhoAdd() {
        return whoAdd;
    }

    public void setWhoAdd(String whoAdd) {
        this.whoAdd = whoAdd;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    //不包含bId，viewed
    public Book(String name, String author, int cId, String publication, String publicationDate, String coverPhotoPath, String coverPhotoUrl,  String digest, String whoAdd) {
        this.name = name;
        this.author = author;
        this.cId = cId;
        this.publication = publication;
        this.publicationDate = publicationDate;
        this.coverPhotoUrl = coverPhotoUrl;
        this.coverPhotoPath = coverPhotoPath;
        this.digest = digest;
        this.whoAdd = whoAdd;
    }

    //不包含bId，viewed，coverPhoto，修改用
    public Book(int bId,String name, String author, int cId, String publication, String publicationDate, String digest, String whoAdd) {
        this.bId = bId;
        this.name = name;
        this.author = author;
        this.cId = cId;
        this.publication = publication;
        this.publicationDate = publicationDate;
        this.digest = digest;
        this.whoAdd = whoAdd;
    }
    //不包含bId，viewed，coverPhoto，who，digest查询显示用
    public Book(int bId,String name, String author, int cId, String publication, String publicationDate) {
        this.bId = bId;
        this.name = name;
        this.author = author;
        this.cId = cId;
        this.publication = publication;
        this.publicationDate = publicationDate;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "BId=" + bId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", cId=" + cId +
                ", publication='" + publication + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", coverPhotoUrl='" + coverPhotoUrl + '\'' +
                ", coverPhotoPath='" + coverPhotoPath + '\'' +
                ", digest='" + digest + '\'' +
                ", viewed='" + viewed + '\'' +
                ", whoAdd='" + whoAdd + '\'' +
                '}';
    }
}
