package study.servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 17/9/2022 13 : 30
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletContext的作用一: 获取全局的初始化参数
        String initParameter = getServletContext().getInitParameter("username");
        System.out.println("我获得的参数是" + initParameter);

        //ServletContext的作用二: 作为全局的域对象在各个Servlet中进行数据共享
        ServletContext servletContext = getServletContext();
        String str = "范海伦";
        servletContext.setAttribute("str",str);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
