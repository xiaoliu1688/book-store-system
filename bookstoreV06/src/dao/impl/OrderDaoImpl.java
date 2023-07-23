package dao.impl;

import bean.Order;
import dao.BaseDao;
import dao.OrderDao;
import utils.PoolConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 刘翰林
 * @create 2023-01-02 16:42
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {


    @Override
    public void insertOrder(Order order) {
        Connection connection = null;

        try {
            //往t_order中插入一条信息，并且返回其主键自增id
            String sql = "insert into t_order (order_sequence,create_time,total_count,total_amount,order_status,user_id) values (?,?,?,?,?,?)";
            connection = PoolConnectionUtils.GetConnectionDruid();
            //预编译，并且指定获取自增长主键
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //设置参数
            preparedStatement.setObject(1, order.getOrderSequence());
            preparedStatement.setObject(2, order.getCreateTime());
            preparedStatement.setObject(3, order.getTotalCount());
            preparedStatement.setObject(4, order.getTotalAmount());
            preparedStatement.setObject(5, order.getOrderStatus());
            preparedStatement.setObject(6, order.getUserId());

            //执行sql语句
            preparedStatement.executeUpdate();
            //获取自增长主键值
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);//也就是获得了主键自增id
                //将orderId存入到order对象中
                order.setOrderId(orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
}
