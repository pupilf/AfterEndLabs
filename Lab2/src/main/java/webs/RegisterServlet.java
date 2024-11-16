package webs;

import POJO.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService =new UserService();

    public RegisterServlet() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();

        if(userService.selectByUsername(username)!=null){  //check whether user already existed
            req.setAttribute("status","username have been used");
            req.getRequestDispatcher("/Register.jsp").forward(req,resp);
        }else{
            userService.insertUser(new User(username,password));
            req.setAttribute("status","register success,please login");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req,resp);
    }
}
