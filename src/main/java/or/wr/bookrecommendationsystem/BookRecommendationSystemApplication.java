package or.wr.bookrecommendationsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("or.wr.bookrecommendationsystem.mapper")
@SpringBootApplication
public class BookRecommendationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommendationSystemApplication.class, args);

    }

}
