package servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 9/10/2022 15 : 34
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PortalServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("index",request,response);
    }
}
