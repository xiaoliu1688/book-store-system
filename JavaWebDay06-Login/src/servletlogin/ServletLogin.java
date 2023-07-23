package servletlogin; /**
 * @author:刘翰林
 * @Description:
 * @Date: 23/9/2022 12 : 57
 */

import bean.user;
import utils.PoolConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //解决请求参数和响应数据的乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获得请求参数
        System.out.println("连接数据库的类被访问了");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.获得连接

        Connection connection = null;
        user query = null;
        try {
            System.out.println("sadfasdf:" + connection);
            connection = PoolConnectionUtils.GetConnectionDruid();
            String sql = "select * from login where username = ? and password = ?";
            BeanHandler<user> handler = new BeanHandler(user.class);
            QueryRunner runner = new QueryRunner();
            query = runner.query(connection, sql, handler, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if(connection != null)
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //将查到的数据响应给客户端
        response.getWriter().write(query.toString());

    }
}
