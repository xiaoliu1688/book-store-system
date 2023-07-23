package servelets; /**
 * @author 刘翰林
 * @create 2023-01-05 19:51
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Demo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        response.getWriter().write("hello  world!!");
    }
}
