package servlet; /**
 * @author 刘翰林
 * @create 2022-10-24 18:17
 */

import bean.Soldier;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.SoldierServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

public class UpdateServlet extends HttpServlet {

    SoldierServiceImpl soldierService = new SoldierServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //1、获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2、将map封装到一个soldier对象里
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3、调用业务逻辑层进行修改
            System.out.println(soldier);
            soldierService.update(soldier);
            //4、跳转到显示页面
            response.sendRedirect( request.getContextPath()+ "/showAll");//重定向由浏览器重新发请求到showAllServlet访问showall页面
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
