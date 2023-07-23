package servlets.model; /**
 * @author 刘翰林
 * @create 2022-10-30 15:25
 */

import servlets.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends ModelBaseServlet {
    /**
     * 跳转到后台管理界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toAdminPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("manager/manager",request,response);
    }

}
