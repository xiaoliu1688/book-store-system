package study.servlet;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.jws.WebService;
import javax.servlet.*;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 13/9/2022 10 : 15
 *
 * 把写好的web程序部署到tomcat服务器上边，访问tomcat服务器就是本机的地址，访问服务器上的web应用就是已经部署好的，在左上角的锤子旁边
 * 然后在web.xml文件中配置了映射地址，我们写的是Java程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务器中注册我们
 * 写的servlet，还需要给他一个浏览器能访问的路径
 *
 * 使用Servlet的时候可能会遇到一个问题: 你会发现根本没有Servlet这个接口
 * 原因是Servlet接口不是JDK中的，我们需要导入额外的jar包才能使用Servlet,因为Servlet的jar包是内置在Tomcat服务器中的
 * 所以我们只需要添加对Tomcat服务器的依赖就可以了
 *
 * 编写Servlet的步骤:
 * 1. 编写一个类实现Servlet接口，并重写sercvice()方法接收和处理请求
 * 2. 给Servlet配置映射路径:
 *    2.1 方式一: 使用xml配置(重点掌握)
 *    2.2 方式二: 使用注解配置(了解) 在当前Servlet类上添加@WebServlet("/demo01")
 *
 * 3. Servlet的生命周期:
 *    3.1 什么时候创建: Tomcat在第一次接收到访问该Servlet的请求的时候，会创建出该Servlet的对象
 *    3.2 什么时候销毁: Tomcat服务器关闭的时候销毁它里面所有的Servlet
 * 4. Servlet的生命周期方法: 在Servlet的生命周期内必然会执行的方法
 *   4.1 init()该方法会在Servlet对象被创建出来之后执行: 通常在它里面做一些初始化工作或者耗时工作
 *   4.2 service()方法会在该Servlet每次接收到请求的时候执行: 通常就是处理请求
 *   4.3 destroy()方法会在该Servlet对象被销毁之前执行: 通常做一些资源回收、销毁操作
 *
 * 5. 将Servlet的创建时机提前到服务器启动的时候,在web.xml中配置
 */
public class ServletDemo01 implements Servlet {
    //init()该方法会在Servlet对象被创建出来之后执行: 通常在它里面做一些初始化工作或者耗时工作
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet对象创建了...");
        String username = servletConfig.getInitParameter("username");
        System.out.println(username);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("被访问了...");
//        InputStream ips = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
//        Properties properties = new Properties();
//        properties.load(ips);
//        try {
//            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//            Connection connection = dataSource.getConnection();
//            QueryRunner runner = new QueryRunner();
//            String sql = "";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        servletResponse.getWriter().write("com to my web");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("对象被销毁");
    }
    @Test
    public void test1(){
        File file = new File("druid.properties");
        file.delete();
    }

}