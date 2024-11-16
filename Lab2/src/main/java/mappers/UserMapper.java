package mappers;

import POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select *from user where username=#{username} and password=#{password}")
    User getUser(@Param("username") String name, @Param("password") String password);

    @Select("select *from user where username=#{name}")
    User selectByUsername(String name);

    @Insert("insert into user(username,password) value(#{username},#{password})")
    void insertUser(User user);
}
