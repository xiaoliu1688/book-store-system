package study.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 17/9/2022 11 : 18
 *
 * 直接继承HttpServlet编写Servlet
 * 1. 写一个类继承HttpServlet
 * 2. 重写doGet和doPost方法
 * 3. 配置Servlet的映射路径
 */
public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果客户端发送的请求是get请求，那么就会执行该Servlet的doGet()方法
        System.out.println("发送了一个get请求");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果客户端发送的请求是post请求，那么就会执行该Servlet的doPost()方法
        System.out.println("发送了一个post请求");
    }
}