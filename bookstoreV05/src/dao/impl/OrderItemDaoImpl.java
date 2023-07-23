package dao.impl;

import bean.OrderItem;
import dao.BaseDao;
import dao.OrderItemDao;

/**
 * @author 刘翰林
 * @create 2023-01-02 21:17
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void insertOrderItemArr(Object[][] insertOrderItemParamArr) {
        String sql = "insert into t_order_item (book_name,price,img_path,item_count,item_amount,order_id) values (?,?,?,?,?,?)";
        int[] ints = batchUpdate(sql, insertOrderItemParamArr);

    }
}
