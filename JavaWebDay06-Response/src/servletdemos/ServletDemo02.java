package servletdemos; /**
 * @author:刘翰林
 * @Description:
 * @Date: 23/9/2022 10 : 56
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo02接收到了一个请求...");
        //跳转到ServletDemo03
        //采用重定向跳转
        //方式一: 通过设置响应行和响应头进行完成
//        response.setStatus(302);
//        response.setHeader("location","/JavaWebDay06_Response/demo03");//可以理解为计算机网络中设置了报文的目的地址

        //方式二: 通过调用response对象的sendRedirect("路径")发起重定向
//        response.sendRedirect("/web0602/demo03");
        response.sendRedirect("/JavaWebDay06_Response/demo03");
    }
}
