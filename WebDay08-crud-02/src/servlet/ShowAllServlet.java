package servlet; /**
 * @author:刘翰林
 * @Description:
 * @Date: 9/10/2022 15 : 59
 */

import bean.Soldier;
import service.SoldierService;
import service.impl.SoldierServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ShowAllServlet extends ViewBaseServlet {
    SoldierService soldierService = new SoldierServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Soldier> allSoldier = soldierService.findAllSoldier();
            Iterator<Soldier> iterator = allSoldier.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
//            System.out.println(allSoldier);
            request.setAttribute("SoldierList",allSoldier);
            processTemplate("list",request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
