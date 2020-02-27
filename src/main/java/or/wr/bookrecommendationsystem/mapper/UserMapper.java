package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    String findUsername(String username);

    String findEmail(String email);

    String checkPasswordByUsername(String username);

    String checkPasswordByEmail(String email);

    String findUsernameByEmail(String email);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    int updateEmailByUsername(@Param("username") String username, @Param("email") String email);

    int addBookToDesk(@Param("user") String user, @Param("bId") int bId);

    int findBIdByUserFDesk(@Param("user") String user, @Param("bId") int bId);

    ArrayList<Integer> findAllBIdByUserFDesk(String user);

    int deleteDBookByBIdFDesk(@Param("user") String user, @Param("bId") int bId);


}
