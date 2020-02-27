package or.wr.bookrecommendationsystem.entity;


public class BatchBook{

    private String name;
    private String author;
    private String cId;
    private String publication;
    private String publicationDate;
    private String digest;

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

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public BatchBook(String name, String author, String cId, String publication, String publicationDate, String digest) {
        this.name = name;
        this.author = author;
        this.cId = cId;
        this.publication = publication;
        this.publicationDate = publicationDate;
        this.digest = digest;
    }
}
