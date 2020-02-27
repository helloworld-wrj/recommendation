package or.wr.bookrecommendationsystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TUserMapper {

    @Insert("insert into rt_tusers(username,email,tcode,validity) values(#{username},#{email}, #{code}, #{validity})")
    int addTUser(String username, String email, String code, String validity);

    @Update("update rt_tusers set tcode = #{code}, validity = #{validity} where username = #{username} and email = #{email}")
    int updateCode(String username, String email, String code, String validity);

    @Select("select tcode from rt_tusers where username = #{username} and email = #{email}")
    String findTUserCode(String username, String email);

    @Select("select validity from rt_tusers where username = #{username} and email = #{email}")
    String findTUserValidity(String username, String email);
}
