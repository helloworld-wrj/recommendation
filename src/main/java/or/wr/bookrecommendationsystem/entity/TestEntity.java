package or.wr.bookrecommendationsystem.entity;

import org.springframework.data.annotation.Id;

public class TestEntity {


    private String id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TestEntity(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
