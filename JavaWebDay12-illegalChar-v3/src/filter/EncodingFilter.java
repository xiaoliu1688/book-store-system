package filter;
/**
 * @author 刘翰林
 * @create 2022-12-04 23:19
 */

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //设置字符编码集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        //允许放行
        chain.doFilter(request, response);
    }
}
