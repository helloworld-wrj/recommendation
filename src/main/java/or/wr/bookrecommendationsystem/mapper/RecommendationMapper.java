package or.wr.bookrecommendationsystem.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface RecommendationMapper {

    /*1、操纵 文章指数表 rt_articlesIndex*/
    @Update("update rt_articlesIndex set arHits = arHits + 1 where aId = ${aId}")
    void addArHisByAid(int aId);

    @Update("update rt_articlesIndex set arBrowseIndex = arBrowseIndex + 1 where aId = ${aId}")
    void addArBrowseIndexByAid(int aId);

    @Update("update rt_articlesIndex set arComments = arComments + 1 where aId = ${aId}")
    void addArCommentsIndexByAid(int aId);

    @Delete("delete from rt_articlesIndex where aId = ${aId}")
    void deleteArCommentsIndexByAid(int aId);

    @Select("select aId from rt_articlesIndex where arIndex = (select max(arIndex) from rt_articlesIndex)")
    int findMostPopularAId();

    /*查找除了指定aId外最新的4个aId*/
    @Select("select aId from rt_articles where aId != #{aId} order by aId desc limit 0, 4")
    int[] findFourLastAid(int aId);

    /*2、操纵rt_userHabit, 用户类兴趣表*/

    @Update("update rt_userHabit set cBookIndex = cBookIndex + 1 where cId = ${cId} and username = #{username}")
    void addCBookIndex(String username, int cId);

    @Update("update rt_userHabit set cBookIndex = cBookIndex - 1 where cId = ${cId} and username = #{username}")
    void deleteCBookIndex(String username, int cId);

    @Update("update rt_userHabit set cHits = cHits + 1 where cId = ${cId} and username = #{username}")
    void addCHits(String username, int cId);

    @Update("update rt_userHabit set cBrowseIndex = cBrowseIndex + 1 where cId = ${cId} and username = #{username}")
    void addCBrowseIndex(String username, int cId);

    @Select("select max(cIndex) from rt_userHabit where username = #{username}")
    int checkNewUser(String username);

    @Select("select cId from rt_userHabit where username = #{username} order by cIndex desc limit 0,3; ")
    int[] findTopCIndexByUsername(String username);




    /*3、操作reToLoginUsers*/

    //更新登录后用户的推荐表
    @Update("update rt_reToLoginUsers set reAid = #{aId} where reIndex = #{reIndex} and username = #{username}")
    void updateReAidToRLU(int reIndex, String username, int aId);

    @Insert("update rt_reToLoginUsers set reAid = #{aId} where username = #{username} and reAid is null")
    void updateReAidByNull(String username, int aId);

    //检查登录后用户的推荐表，是否够5
    @Select("select count(reAid) from rt_reToLoginUsers where reAid is not null and username = #{username};")
    int checkNumbersOfReAid(String username);

    //清空reAid，删除文章时用到
    @Update("update rt_reToLoginUsers set reAid = null where reAid = #{aI}")
    void deleteReAid(int aId);

    //当删除文章涉及到推荐文章时，使与rt_reArticles保持一致
    @Update("update rt_reToLoginUsers set reAid = ${aId} where reIndex = #{reIndex} and username = #{username}")
    void updateKeepNewReAid(int reIndex, int aId, String username);

    @Select("select reAid from rt_reToLoginUsers where reIndex = #{reIndex} and username = #{username}")
    int findReAidByReIndexAndUser(int reIndex, String username);

    @Select("select reAid from rt_reToLoginUsers where username = #{username}")
    int[] findAllReAidByUsername(String username);

    /*3、操作首页默认推荐表 rt_reArticles*/
    @Delete("delete from rt_reArticles where aId = #{aId}")
    int deleteFromReArticleByAId(int aId);



}
