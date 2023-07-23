package servlet; /**
 * @author 刘翰林
 * @create 2022-11-13 16:30
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
        //1、将字符串存入会话域
        HttpSession session = request.getSession();
        String username = "liu";
        session.setAttribute("username",username);
    }
}
