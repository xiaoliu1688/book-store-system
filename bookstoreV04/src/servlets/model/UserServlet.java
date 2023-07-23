package servlets.model; /**
 * @author 刘翰林
 * @create 2022-10-29 13:19
 */

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.UserServiceImpl;
import servlets.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserServlet extends ModelBaseServlet {

    UserServiceImpl userService = new UserServiceImpl();
    /**
     * 跳转到登录界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/login",request,response);
    }


    /**
     * 登录校验
     * @date
     * @author
     * @return
     * @throws
     */
    public void doLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(null,username,password,null);
        System.out.println("asdfasdfasdf");
        try {
            System.out.println(user);
            userService = new UserServiceImpl();
            User user1 = userService.doLogin(user);//若user存在则返回该user

            request.getSession().setAttribute("loginUser",user1);//将user1对象存入会话域

            //没有出现异常说明登录成功，跳转到登录成功页面
            processTemplate("user/login_success",request,response);

        } catch (Exception e) {
            //登录失败，往域对象中存储登录失败的信息
            request.setAttribute("errorMessage","登录失败，" + e.getMessage());

            //跳转到登录界面
            processTemplate("user/login",request,response);
        }
    }

    /**
     * 跳转到注册界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toRegisterPage (HttpServletRequest request,HttpServletResponse response) throws IOException{
        processTemplate("user/regist",request,response);
    }

    /**
     * 注册校验
     * @date
     * @author
     * @return
     * @throws
     */
    public void doRegister (HttpServletRequest request,HttpServletResponse response) throws IOException{
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        String code = parameterMap.get("code")[0];//获取表单中用户输入的验证码的值
        String checkCode = String.valueOf(request.getSession().getAttribute("checkCode"));//获取会话域KaptchaServlet中自动生成的验证码的值
        try {
            if(code.equalsIgnoreCase(checkCode)){

                //2. 使用BeanUtils将parameterMap中的数据封装到User对象
                User user = new User();


                BeanUtils.populate(user,parameterMap);
                System.out.println(user);
                //3. 调用业务层的方法处理注册业务
                userService.doRegister(user);
                //没有异常，就是注册成功
                //跳转到注册成功页面
                processTemplate("user/regist_success",request,response);
            }else {
                throw new RuntimeException("验证码错误");
            }
        } catch (Exception e) {
            //1、向请求域中写入错误内容
            request.setAttribute("errorMessage","注册失败，" + e.getMessage());
            //2、回到注册界面
            processTemplate("user/regist",request,response);
        }
    }

    /**
     * 退出登录
     * @date
     * @author
     * @return
     * @throws
     */
    public void logout (HttpServletRequest request,HttpServletResponse response) throws IOException{

        request.getSession().invalidate();//让本次会话立即失效
        //重定向跳转到主页
        response.sendRedirect(request.getContextPath() + "/index.html");

    }


}
