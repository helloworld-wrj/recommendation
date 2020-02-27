package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.Article;

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



}
