package filter; /**
 * @author 刘翰林
 * @create 2023-01-04 17:28
 */

import utils.PoolConnectionUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String orderSequence = null;
        Connection connection = null;
        try {
            connection = PoolConnectionUtils.GetConnectionDruid();
            //开启事务
            connection.setAutoCommit(false);//关闭自动提交
            chain.doFilter(request, response);

            connection.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();//出现异常回滚

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
