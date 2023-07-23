package filter; /**
 * @author 刘翰林
 * @create 2022-12-04 23:47
 */

import javax.servlet.*;
import java.io.IOException;

public class IllegalCharFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        String commentValue = request.getParameter("commentValue");
        if(commentValue != null){
            System.out.println("fsdfsdf");

            if(commentValue.contains("你大爷的")){
                System.out.println("fsdfsdf");
                response.getWriter().write("有敏感词，响应失败");
                return;
            }
        }
        chain.doFilter(request, response);

    }
}
