package Servlet; /**
 * @author 刘翰林
 * @create 2022-11-13 14:44
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、创建一个cookie对象对象用来,注意cookie里只能存放英文字符串并且不能有空格
        String user = "liuhanlin";
        Cookie cookie = new Cookie("username",user);
        //2、将cookie添加到响应中

        response.addCookie(cookie);
//        cookie.setMaxAge(60*60*24);这里的参数是设置cookie的有效期秒数
        //我们还可以设置cookie的domain和path
        //设置了domain就可以指定cookie的域名，设置path就可以指定cookie使用具体的范围

        //我们当前只要指定path为当前的项目部署名
//        cookie.setPath(request.getContextPath());
        //如果要删除一个cookie就在代码中创建一个同名，同路劲的cookie，然后SetMaxAge（0）。
    }
}
