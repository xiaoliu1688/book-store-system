package servlets.model; /**
 * @author 刘翰林
 * @create 2023-01-02 16:25
 */

import bean.Cart;
import bean.User;
import constants.bookConstants;
import service.impl.OrderServiceImpl;
import servlets.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderClientServlet extends ModelBaseServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();
    /**
     * 结算购物车
     * @date
     * @author
     * @return
     * @throws
     */
    public void checkout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //1、从session中获取购物车信息
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(bookConstants.CARTSESSIONKEY);
            //2、从session中获取用户信息
            User user = (User) session.getAttribute(bookConstants.USERSESSIONKEY);
            //3、调用业务层方法进行结算，返回订单号
            String orderSequence = orderService.checkout(user, cart);
            //4、清空购物车
            session.removeAttribute(bookConstants.CARTSESSIONKEY);
            //5、将订单号存储到请求域中
            request.setAttribute("orderSequence",orderSequence);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            //6、跳转到checkout.html界面
            processTemplate("cart/checkout",request,response);
        }

    }
}
