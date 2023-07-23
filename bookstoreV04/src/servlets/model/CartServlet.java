package servlets.model; /**
 * @author 刘翰林
 * @create 2022-11-25 10:42
 */

import bean.Book;
import bean.Cart;
import service.impl.BookServiceImpl;
import servlets.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CartServlet extends ModelBaseServlet {
    BookServiceImpl bookService = new BookServiceImpl();
    /**
     * 添加购物项进购物车
     * @date
     * @author
     * @return
     * @throws
     */
    public void addCartItem(HttpServletRequest request,HttpServletResponse response){
        //获取请求参数里携带的书的id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //从会话域里拿到购物车对象，注意这里的拿到是指cart变量指向会话域里的cart对象，并不是重新new了一个对象
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        try {
            //通过id查出书本
            Book book = bookService.findBookById(id);
            //判断购物车是否为空
            if(cart == null){
                //如果为空则新建一个购物车
                cart = new Cart();
                //将书本存入cart中，并把cart存入会话域
                cart.addBookToCart(book);
                session.setAttribute("cart",cart);
            }else {
                //如果购物车不为空，则直接把书加入到购物车中
                cart.addBookToCart(book);//这里不用把购物车存入会话域的原因是，这个cart对象就是指向的会话域里面的cart对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //最后跳转到首页
        System.out.println(cart);
        try {
//            request.getRequestDispatcher(request.getContextPath() + "/index.html").forward(request,response);
            response.sendRedirect( request.getContextPath() + "/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到购物车界面
     * @date
     * @author
     * @return
     * @throws
     */
    public void toCartPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        processTemplate("cart/cart",request,response);
    }

    /**
     * 清空购物车
     * @date
     * @author
     * @return
     * @throws
     */
    public void cleanCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //将会话域中的cart对象移除
        request.getSession().removeAttribute("cart");
        //跳转到购物车界面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 购物项数量减一
     * @date
     * @author
     * @return
     * @throws
     */
    public void countDecrease(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //先获取请求参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        //从会话域中取出cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //通过cart修改购物项的数量
        cart.itemCountDecrease(id);
        //重新跳转到购物车界面
        processTemplate("cart/cart",request,response);

    }


    /**
     * 购物项数量减一
     * @date
     * @author
     * @return
     * @throws
     */
    public void countIncrease(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //先获取请求参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        //从会话域中取出cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //通过cart修改购物项的数量
        cart.itemCountIncrease(id);
        //重新跳转到购物车界面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 删除购物项
     * @date
     * @author
     * @return
     * @throws
     */
    public void removeCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //先获取请求参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        //从会话域中取出cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //通过cart删除购物项的数量
        cart.removeCartItem(id);
        //重新跳转到购物车界面
        processTemplate("cart/cart",request,response);
    }
    /**
     * 通过修文本框修改数量
     * @date
     * @author
     * @return
     * @throws
     */
    public void updateCartItemCount(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //先获取参数
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer newCount = Integer.valueOf(request.getParameter("newCount"));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.updateItemCount(id,newCount);
        processTemplate("cart/cart",request,response);
    }


}
