package service;

import bean.Cart;
import bean.User;

import java.sql.SQLException;

/**
 * @author 刘翰林
 * @create 2023-01-02 16:38
 */
public interface OrderService {
    /**
     * 结算业务
     * @date
     * @author
     * @return
     * @throws
     */
    String checkout(User user,Cart cart) throws SQLException;
}
