package servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init servelt");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service method");
        System.out.println(servletRequest.toString());
        System.out.println(servletResponse.toString());
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet destory");
    }
}
