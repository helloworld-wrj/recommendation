package or.wr.bookrecommendationsystem.entity;

import java.io.Serializable;

public class ClassicSaying implements Serializable {
    private int id;
    private String content;
    private String provenance;

    public ClassicSaying(int id, String content, String provenance) {
        this.id = id;
        this.content = content;
        this.provenance = provenance;
    }

    public String getContent() {
        return content;
    }

    public String getProvenance() {
        return provenance;
    }

    @Override
    public String toString() {
        return "ClassicSaying{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", provenance='" + provenance + '\'' +
                '}';
    }
}
