package servlet; /**
 * @author 刘翰林
 * @create 2022-10-23 16:08
 */

import service.impl.SoldierServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {

    SoldierServiceImpl soldierService = new SoldierServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求参数，得到需要删除的id
        Integer soldierId = Integer.valueOf(request.getParameter("id"));
        //2、调用业务逻辑层删除
        try {
            soldierService.deleteSoldier(soldierId);
            //3、删除后跳转到显示界面
            response.sendRedirect( request.getContextPath()+ "/showAll");//重定向由浏览器重新发请求到showAllServlet访问showall页面
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
