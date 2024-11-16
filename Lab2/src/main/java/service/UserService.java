package service;


import POJO.User;
import mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import utils.sqlSessionUtil;

import java.io.IOException;

//Business logic layer
public class UserService{
    private SqlSessionFactory factory= sqlSessionUtil.getSqlSessionFactory();

    public UserService() throws IOException {
    }

    public User getUser(String username,String password){
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUser(username, password);
        sqlSession.close();
        return user;
    }

    public User selectByUsername(String name) {
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByUsername(name);
        sqlSession.close();
        return user;
    }

    public void insertUser(User user) {
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insertUser(user);
        sqlSession.close();
    }
}
