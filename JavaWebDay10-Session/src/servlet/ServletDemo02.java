package servlet; /**
 * @author 刘翰林
 * @create 2022-11-13 16:36
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
        //从会话域中获取username
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        System.out.println(username);
        //session的底层其实是cookie，当客户端发请求给服务器servlet时，调用request.getSession方法时，查看当前请求
        //具体底层原理见e盘javaweb学习截图
//        session.invalidate();
//        是的session立即失效
    }
}
