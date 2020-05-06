package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    int addArticle(Article article);

    Article findTitleAndContentPathByAID(int aId);

    Article findTitleByAId(int aId);

    String findContentPathByAId(int aId);

    //4、根据aId查找aid,title,classification,digest
    Article findATCDByAId(int aId);

    //5、模糊查询（title,classification,digest)
    List<Article> findATCDByKey(String key);

    //7、查找封面图片路径
    String findCoverPhotoPath(int aId);

    String findCoverPhotoUrl(int aId);

    //8
    int deleteArticleByAId(int aId);

    int checkAId(int aId);

    //select aid, title, digest, classificationValue, path
    Article selectToUpdate(int aId);

    boolean updateArticle(Article article);


    //设置推荐
    int setReArticle(int id, int aId, int reIndex);

    //根据reIndex从推荐表查找aId
    int findAIdFromReArticle(int reIndex);

    Article findArticleDynamic(int page);

    int countArticles();


    /*查找除了指定aId外最新的4个aId*/
    @Select("select aId from rt_articles where aId != #{aId} order by aId desc limit 0, 4")
    int[] findFourLastAid(int aId);

    /*查找除了指定aId外最新的1个aId*/
    @Select("select aId from rt_articles where aId != #{aId} order by aId desc limit 0, 1")
    int findOneLastAid(int aId);

    //查找一个不在指定用户推荐表中最新的aId
    @Select("select aId from rt_articles where aId not in (select reAid from rt_reToLoginUsers where username = '#{username}' and reAid is not null) order by aId desc limit 0, 1;")
    int findLatestAIdByReLoginUser(String username);

    //根据cId查找最新的一篇文章的aId
    @Select("select aId from rt_articles where cId = #{cId} order by aId desc limit 0,1")
    int findLatestAidByCid(int cId);

    //根据cId统计改类文章数
    @Select("select count(aId) from rt_articles where cId = ${cId}")
    int countArticlesNumsByCid(int cId);



}
