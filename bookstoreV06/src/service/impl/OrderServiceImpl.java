package service.impl;
import bean.Cart;
import bean.CartItem;
import bean.Order;
import bean.User;
import constants.bookConstants;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import service.OrderService;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 刘翰林
 * @create 2023-01-02 16:40
 */
public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public String checkout(User user, Cart cart) throws SQLException {
        String orderSequence = null;
        Order order = new Order();
        //1、往订单中插入一条数据
        //1.1生成一个唯一的订单号使用uuid工具类
        orderSequence = UUID.randomUUID().toString();
        order.setOrderSequence(orderSequence);
        //1.2生成当前时间createTime
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = dateFormat.format(new Date());
        order.setCreateTime(createTime);
        //1.3订单的totalCount就是购物车的totalCount
        order.setTotalCount(cart.getTotalCount());
        //1.4订单的totalAmount就是购物车的totalAmount
        order.setTotalAmount(cart.getTotalAmount());
        //1.5设置订单状态为0，即未发货状态
        order.setOrderStatus(bookConstants.UNFILLEDORDER);
        //1.6订单的userid就是用户的userid
        order.setUserId(user.getUserId());
        //1.7调用持久层方法添加订单数据
        orderDao.insertOrder(order);
        //2、往订单详情里批处理添加多条信息，调用orderItemImpl对象的insertOrderItemArr（）方法
        //2.1需要先获取参数二维数组，第一维表示有多少个要插入的对象，第二位表示要插入的对象有多少列
        //插入对象的总数就是购物车里购物项的数目，所以我们先获得购物车的所有购物项
        Collection<CartItem> values = cart.getCartItemMap().values();//获得了所有的购物项组成的集合
        List<CartItem> list = new ArrayList(values);
        Object[][] objectsOrder = new Object[list.size()][6];
        Object[][] objectsBook = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            //获取每个购物项
            CartItem cartItem = list.get(i);
            //封装批量添加订单项的参数*********************

            //封装sql语句中的第一个参数book_name的值，就是cartItem的bookName
            objectsOrder[i][0] = cartItem.getBookName();
            //就是cartItem的price
            objectsOrder[i][1] = cartItem.getPrice();
            //就是cartItem的imgPath
            objectsOrder[i][2] = cartItem.getImgPath();
            //就是cartItem的count
            objectsOrder[i][3] = cartItem.getCount();
            //就是cartItem的amount
            objectsOrder[i][4] = cartItem.getAmount();
            //orderId就是订单的id,就是第一步获取的自增长的主键值
            objectsOrder[i][5] = order.getOrderId();

            //批量修改书的销量和库存的参数

            //库存和销量的变化量就是购物项的数量
            objectsBook[i][0] = cartItem.getCount();
            objectsBook[i][1] = cartItem.getCount();
            //书的id就是购物项的bookid
            objectsBook[i][2] = cartItem.getBookId();


        }
        orderItemDao.insertOrderItemArr(objectsOrder);
        //3、批量更新t_book表里的sales和stock
        bookDao.updateBookArr(objectsBook);
        //4、返回订单号
        return orderSequence;

    }
}
