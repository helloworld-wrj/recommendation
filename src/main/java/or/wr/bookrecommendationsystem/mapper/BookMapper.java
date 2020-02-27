package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.Article;
import or.wr.bookrecommendationsystem.entity.Book;
import or.wr.bookrecommendationsystem.entity.Classification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface BookMapper {

    List<Classification> findAllClassification();

    int addBook(Book book);

    int deleteOneBookByBId(int bId);

    String findCoverPhotoPath(int bId);

    int findBidByNameAndAuthor(String name, String author);

    int updateByBId(Book book);

    List<Book> findBooksByFive(Book book);

    Book findBookByBId(int bId);

    Book findLatestBook();

    List<Book> findBookByDynamic(int page);

    int countBooks();

    List<Book> findBooksByCId(int cId);

    //根据书名、作者查找
    ArrayList<Book> findBooksByLikeKey(String key);


}
