package filter; /**
 * @author 刘翰林
 * @create 2023-01-01 20:12
 */

import constants.bookConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //拦截之后判断用户是否登录
        //如果已经登录则放行，如果未登录就跳转到登录界面
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        if(request1.getSession().getAttribute(bookConstants.USERSESSIONKEY) == null){
            //如果此会话域对象为空，则表示未登录
            //在请求域中添加错误信息
            request1.setAttribute("errorMessage","请先登录账户再进入购物车!");
            //跳转到登录界面
            request1.getRequestDispatcher("/user?method=toLoginPage").forward(request1,response1);
        }
        //已经登录则放行进入购物车
        chain.doFilter(request, response);
    }
}
