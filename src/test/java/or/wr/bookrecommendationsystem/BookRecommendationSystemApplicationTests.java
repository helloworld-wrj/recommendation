package or.wr.bookrecommendationsystem;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRecommendationSystemApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.trace("this is trace");
        logger.debug("this is debug");
        logger.info("this is info");
        logger.warn("this is warm");
        logger.error("this is error");
    }

}
