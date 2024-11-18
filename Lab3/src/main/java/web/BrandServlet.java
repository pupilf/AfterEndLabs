package web;

import POJO.Brand;
import POJO.Page;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BrandService;
import service.imp.BrandServiceImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends HttpServlet {
    private BrandService service=new BrandServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if("/selectAll".equals(pathInfo))
            selectAll(req,resp);
        else if("/selectByPage".equals(pathInfo))
            selectByPage(req,resp);
        else if("/selectByCondition".equals(pathInfo))
            selectByCondition(req,resp);
        else resp.getWriter().write("no method");
    }

    private void selectByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = contentBuilder.toString();
        Brand brand = JSON.parseObject(content, Brand.class);
        List<Brand> brands = service.selectByCondition(brand);
        String jsonString = JSON.toJSONString(brands);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String curPage = req.getParameter("curPage");
        String pageSize = req.getParameter("pageSize");
        Page<Brand> page = service.selectByPage(Integer.parseInt(curPage), Integer.parseInt(pageSize));
        String jsonString = JSON.toJSONString(page);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Brand> brands = service.selectAll();
        resp.setContentType("application/json;charset=utf-8");
        String jsonString = JSON.toJSONString(brands);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        add(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            // 读取请求体内容
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line);
                }
            }
            String content = contentBuilder.toString();

            // 解析 JSON 内容为 Brand 对象
            if (content != null && !content.isEmpty()) {
                Brand brand = JSON.parseObject(content, Brand.class);  // 解析成 Brand 类
                service.add(brand);
                resp.getWriter().write("add success");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Request body is empty.");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        update(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // 读取请求体内容
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line);
                }
            }
            String content = contentBuilder.toString(); //得到完整的请求JSON串
            System.out.println("content = " + content);
            if (!content.isEmpty()) {
                Brand brand = JSON.parseObject(content, Brand.class);  // 解析成 Brand 类
                service.update(brand);
                resp.getWriter().write("update success");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Request body is empty.");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if("/remove".equals(pathInfo))
            remove(req,resp);
        else if("/batchRemove".equals(pathInfo))
            batchRemove(req,resp);
        else resp.getWriter().write("no method");
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = req.getReader().readLine();
        Integer id = JSON.parseObject(content, Integer.class); //发送的JSON只能是一个数字而不能是键值对
        service.remove(id);
        resp.getWriter().write("remove success");
    }

    private void batchRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String content = req.getReader().readLine();
            int[] ids = JSON.parseObject(content, int[].class);
            service.batchRemove(ids);
            resp.getWriter().write("batch remove success");
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
