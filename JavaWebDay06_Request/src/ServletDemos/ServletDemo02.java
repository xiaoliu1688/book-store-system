package ServletDemos; /**
 * @author:刘翰林
 * @Description:
 * @Date: 22/9/2022 10 : 43
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

        System.out.println("demo02被访问了");
        String name = (String) request.getAttribute("name");
        System.out.println("name:" + name);
    }
}
