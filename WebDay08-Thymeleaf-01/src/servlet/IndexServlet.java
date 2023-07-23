package servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 26/9/2022 21 : 25
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转到页面Index,是ViewBaseServlt里的方法，传入的参数为访问页面的名字、请求和响应
        processTemplate("index",request,response);
    }
}
