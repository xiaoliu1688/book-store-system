package study.servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 17/9/2022 18 : 42
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext对象
        //第一种方法获取ServletContext
        ServletContext servletContext = getServletContext();
        String str = (String) servletContext.getAttribute("str");
        System.out.println(str);

        //ServletContext的作用一: 获取全局的初始化参数
        String initParameter = getServletContext().getInitParameter("username");
        System.out.println("我获得的参数是" + initParameter);
        //第二种方法是:ServletConfig对象调用getServletContext()方法
//        ServletContext servletContext = getServletConfig().getServletContext();

        //ServletContext的作用三: 获取资源文件的真实路径其实就是动态获取web文件夹里面的资源的物理路径
        //目标: 将img/mm.jpg转成字节输入流

        ServletContext context = getServletContext();
        String realPath = context.getRealPath("img/赵金麦.jpg");
        FileInputStream inputStream = new FileInputStream(realPath);
        System.out.println(realPath);
        System.out.println(inputStream);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
