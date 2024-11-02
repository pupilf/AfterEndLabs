package mappers;

import POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    User getUser(String name, String password);
    @Select("select *from user where username=#{name}")
    User selectByUsername(String name);

    @Insert("insert into user(username,password) value(#{username},#{password})")
    void insertUser(User user);
}
