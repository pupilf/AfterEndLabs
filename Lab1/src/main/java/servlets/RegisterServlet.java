package servlets;

import POJO.User;
import mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import utils.sqlSessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
        //resp.setContentType("text/html");
        if(check(username,password)){  //check whether user already existed
            writer.println("<h1>Already existed username<h1>");
        }else{
            writer.println("<h1>Register success<h1>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req,resp);
    }
    private boolean check(String username, String password) throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        if(mapper.selectByUsername(username)!=null){
            return true;
        }
        User user=new User(username,password);
        mapper.insertUser(user);
        return false;
    }
}
