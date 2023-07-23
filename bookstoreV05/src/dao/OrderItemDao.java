package dao;

/**
 * @author 刘翰林
 * @create 2023-01-02 21:15
 */
public interface OrderItemDao {
    /**
     * 批量添加订单项
     * @date
     * @author
     * @return
     * @throws
     */
    void insertOrderItemArr(Object[][] insertOrderItemParamArr);
}
