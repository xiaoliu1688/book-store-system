package dao;

import bean.Order;

import java.sql.SQLException;

/**
 * @author 刘翰林
 * @create 2023-01-02 16:41
 */
public interface OrderDao {


    /**
     * 插入订单数据
     * @date
     * @author
     * @return
     * @throws
     */
    void insertOrder(Order order) throws SQLException;
}
