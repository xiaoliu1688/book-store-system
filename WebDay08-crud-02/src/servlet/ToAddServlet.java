package servlet; /**
 * @author 刘翰林
 * @create 2022-10-23 12:58
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ToAddServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("add",request,response);
    }
}
