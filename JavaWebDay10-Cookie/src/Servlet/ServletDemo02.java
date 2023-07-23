package Servlet; /**
 * @author 刘翰林
 * @create 2022-11-13 14:48
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
        //1、获取到所有的cookie
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                if("username".equals(name)){

                    String value = cookie.getValue();
                    System.out.println("cookie的名字" + name + "cookie的值" + value);
                }
            }
        }
    }
}
