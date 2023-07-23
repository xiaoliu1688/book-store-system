package servlets; /**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 15 : 32
 */

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

public class ServletRegister extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2. 使用BeanUtils将parameterMap中的数据封装到User对象
        User user = new User();
        try {

            BeanUtils.populate(user,parameterMap);
            System.out.println(user);
            //3. 调用业务层的方法处理注册业务
            userService.doRegister(user);
            //没有异常，就是注册成功
            //跳转到注册成功页面
            response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("注册失败" + e.getMessage());
        }



/**        获取username
//        String username = request.getParameter("username");
//        if("liu123".equals(username)){
//            response.getWriter().write("用户名已经存在，请重新注册！！");
//        }
//        else {
//            response.sendRedirect( request.getContextPath() + "/pages/user/regist_success.html");//重定向
       }
  */
    }
}
