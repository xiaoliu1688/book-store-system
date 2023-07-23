package servlets; /**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 11 : 10
 */

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServletLogin extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

//        //1、获取请求的参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        //2、校验参数受否正确
//        if("liuhanlin".equals(username) && "020704".equals(password)){
//            //getContextPath就是获取部署的项目的路径
//            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.html");
//        }else{
//            response.getWriter().write("登录失败!");
//        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(null,username,password,null);

        try {
            System.out.println(user);
            userService = new UserServiceImpl();
            User user1 = userService.doLogin(user);
            //没有出现异常说明登录成功，跳转到登录成功页面
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("登录失败！" + e.getMessage());
        }
    }
}
