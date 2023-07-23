package servlet; /**
 * @author 刘翰林
 * @create 2022-10-24 17:58
 */

import bean.Soldier;
import service.impl.SoldierServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ToUpdateServlet extends ViewBaseServlet {
    SoldierServiceImpl soldierService = new SoldierServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取获取参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2、通过id查出对应的士兵信息
        try {
            System.out.println(id);
            Soldier soldier = soldierService.findSoldier(id);
            //3、将士兵信息存储到请求域中
            request.setAttribute("soldierInfo",soldier);
            //4、跳转到修改界面
            processTemplate("update",request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
