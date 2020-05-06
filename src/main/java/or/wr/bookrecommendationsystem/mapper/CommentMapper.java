package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.ArticleComment;
import or.wr.bookrecommendationsystem.entity.BookComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into rt_bComments(cmContent, bId, author, cmEditorDate) values(#{cmContent}, #{bId}, #{author}, now())")
    int addBookComment(BookComment bookComment);

    @Select("select * from rt_bComments where bId = #{bId}")
    List<BookComment> findAllBCommentByBId(int bId);

    @Insert("insert into rt_aComments(cmContent, aId, author, cmEditorDate) values(#{cmContent}, #{aId}, #{author}, now())")
    int addArticleComment(ArticleComment articleComment);

    @Select("select * from rt_aComments where aId = #{aId}")
    List<ArticleComment> findAllACommentByAId(int aId);

    @Select("select * from rt_bComments where author = #{author}")
    List<BookComment> findAllBCommentByAuthor(String author);

    @Select("select * from rt_aComments where author = #{author}")
    List<ArticleComment> findAllACommentByAuthor(String author);

    @Delete("delete from rt_bComments where cmId = #{id}")
    int deleteBCommentById(int id);

    @Delete("delete from rt_aComments where cmId = #{id}")
    int deleteACommentById(int id);

    //count the number of comments on articles
    @Select("select count(cmContent) from rt_aComments where aId = #{id} ")
    int countACommentsById(int id);

    //count the number of comments on books
    @Select("select count(cmContent) from rt_bComments where bId = #{id} ")
    int countBCommentsById(int id);



}
