package or.wr.bookrecommendationsystem.entity;

public class Classification {

    private int cId;
    private String value;
    private String symbolImgUrl;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSymbolImgUrl() {
        return symbolImgUrl;
    }

    public void setSymbolImgUrl(String symbolImgUrl) {
        this.symbolImgUrl = symbolImgUrl;
    }

    public Classification(int cId, String value, String symbolImgUrl) {
        this.cId = cId;
        this.value = value;
        this.symbolImgUrl = symbolImgUrl;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "cId=" + cId +
                ", value='" + value + '\'' +
                ", symbolImgUrl='" + symbolImgUrl + '\'' +
                '}';
    }

}
