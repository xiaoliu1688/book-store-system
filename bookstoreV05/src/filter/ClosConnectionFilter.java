package filter; /**
 * @author 刘翰林
 * @create 2023-01-04 16:39
 */

import utils.PoolConnectionUtils;

import javax.servlet.*;
import java.io.IOException;

public class ClosConnectionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PoolConnectionUtils.releaseConnection();
        }
    }
}
