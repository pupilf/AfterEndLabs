package servlets;


import POJO.User;
import mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import utils.sqlSessionUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("enter doGet method");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        if (check(username, password)) {
            pw.write("true");
        } else {
            pw.write("false");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("post method");
        doGet(req, resp);
    }

    private boolean check(String username, String password) throws IOException {
        System.out.println("enter check method");
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUser(username, password);
        sqlSession.close();
        return user!=null;
    }

}
