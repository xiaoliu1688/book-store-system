package servlet; /**
 * @author 刘翰林
 * @create 2022-12-04 23:13
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、拿到评论内容
        String commentValue = request.getParameter("commentValue");
        //2、向客户端输出评论内容
        response.getWriter().write("评论成功" + commentValue);
    }
}
