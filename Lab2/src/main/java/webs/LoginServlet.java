package webs;


import POJO.Brand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BrandService;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    private BrandService brandService = new BrandService();

    //private HashMap<String,String> users=new HashMap<>();
    public LoginServlet() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("enter doGet method");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        String checkCode = req.getParameter("checkCode");

        //验证请求中的checkCode与刚才生成并保留在session中的是否相同
        if (!req.getSession().getAttribute("checkCode").equals(checkCode)) {
            req.setAttribute("status", "checkCode error!!!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        if (userService.getUser(username, password) != null) {
            //System.out.println("can login,forward to jsp----------");
            if ("1".equals(rememberMe)) {
                HttpSession session = req.getSession();
                if(session.getAttribute("admit")==null)
                        session.setAttribute("admit",password);
                System.out.println("admit attribute already set in session...");
            }
            List<Brand> brands = brandService.selectAll();
            req.setAttribute("brands", brands);
            req.setAttribute("login", "yes");
            req.getRequestDispatcher("/brandShow.jsp").forward(req, resp);
        } else {
            //System.out.println("can not login------------");
            req.setAttribute("status", "username or password error!!!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("post method");
        doGet(req, resp);
    }

}
