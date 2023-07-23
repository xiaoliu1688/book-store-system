package servlets.model; /**
 * @author 刘翰林
 * @create 2022-11-25 10:42
 */

import bean.Book;
import bean.Cart;
import bean.CartItem;
import bean.CommonResult;
import constants.bookConstants;
import service.impl.BookServiceImpl;
import servlets.base.ModelBaseServlet;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
        Cart cart = (Cart) session.getAttribute(bookConstants.CARTSESSIONKEY);
        CommonResult commonResult = null;
        try {
            //通过id查出书本
            Book book = bookService.findBookById(id);
            //判断购物车是否为空
            if(cart == null){
                //如果为空则新建一个购物车
                cart = new Cart();
                //将书本存入cart中，并把cart存入会话域
                cart.addBookToCart(book);
                session.setAttribute(bookConstants.CARTSESSIONKEY,cart);
            }else {
                //如果购物车不为空，则直接把书加入到购物车中
                cart.addBookToCart(book);//这里不用把购物车存入会话域的原因是，这个cart对象就是指向的会话域里面的cart对象
            }
            //添加成功,将信息响应给axios
            Integer totalCount = cart.getTotalCount();
            commonResult = CommonResult.ok().setResultData(totalCount);//将购物项总数放入里面
            commonResult.getResultData();
        } catch (SQLException e) {
            //添加失败
            commonResult = CommonResult.error().setMessage("添加失败");
            JsonUtils.writeResult(response,commonResult);
            e.printStackTrace();
        }

        JsonUtils.writeResult(response,commonResult);
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
        request.getSession().removeAttribute(bookConstants.CARTSESSIONKEY);
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
    public void countDecrease(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommonResult commonResult = null;
        try {
            //先获取请求参数
            Integer id = Integer.valueOf(request.getParameter("id"));
            //从会话域中取出cart
            Cart cart = (Cart) request.getSession().getAttribute(bookConstants.CARTSESSIONKEY);
            //通过cart修改购物项的数量
            System.out.println(id + "**************");
            cart.itemCountDecrease(id);
            //响应数据给购物车界面,如果减少成功
            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("totalCount",cart.getTotalCount());
            responseMap.put("totalAmount",cart.getTotalAmount());
            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("错误");
            commonResult = CommonResult.error();
        }
        JsonUtils.writeResult(response, commonResult);

    }


    /**
     * 购物项数量加一
     * @date
     * @author
     * @return
     * @throws
     */
    public void countIncrease(HttpServletRequest request,HttpServletResponse response) throws IOException{
        CommonResult commonResult = null;
        try {
            //先获取请求参数
            Integer id = Integer.valueOf(request.getParameter("id"));
            //从会话域中取出cart
            Cart cart = (Cart) request.getSession().getAttribute(bookConstants.CARTSESSIONKEY);
            //通过cart修改购物项的数量
            cart.itemCountIncrease(id);
            //响应数据给购物车界面,如果减少成功
            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("totalCount",cart.getTotalCount());
            responseMap.put("totalAmount",cart.getTotalAmount());
            Double amount = cart.getCartItemMap().get(id).getAmount();
            responseMap.put("amount",amount);
            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }
        JsonUtils.writeResult(response,commonResult);


    }

    /**
     * 删除购物项
     * @date
     * @author
     * @return
     * @throws
     */
    public void removeCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CommonResult commonResult = null;
        try {//先获取请求参数
            Integer id = Integer.valueOf(request.getParameter("id"));
            //从会话域中取出cart
            Cart cart = (Cart) request.getSession().getAttribute(bookConstants.CARTSESSIONKEY);
            //通过cart删除购物项的数量
            cart.removeCartItem(id);
            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("totalCount",cart.getTotalCount());
            responseMap.put("totalAmount",cart.getTotalAmount());
            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }
        JsonUtils.writeResult(response, commonResult);

    }
    /**
     * 通过修文本框修改数量
     * @date
     * @author
     * @return
     * @throws
     */
    public void updateCartItemCount(HttpServletRequest request,HttpServletResponse response) throws IOException{
        CommonResult commonResult = null;
        try {
            //1. 获取请求参数:id,newCount
            Integer id = Integer.valueOf(request.getParameter("id"));
            Integer newCount = Integer.valueOf(request.getParameter("newCount"));
            //2. 从session中获取购物车信息
            Cart cart = (Cart) request.getSession().getAttribute(bookConstants.CARTSESSIONKEY);
            //3. 调用cart中跟新数量的方法
            cart.updateItemCount(id,newCount);

            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("totalCount",cart.getTotalCount());
            responseMap.put("totalAmount",cart.getTotalAmount());
            Double amount = cart.getCartItemMap().get(id).getAmount();
            responseMap.put("amount",amount);
            commonResult = CommonResult.ok().setResultData(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }

        JsonUtils.writeResult(response,commonResult);
    }


    /**
     * 获取购物车的json数据
     * @param request
     * @param response
     */

    public void getCartJSON(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> responseMap = null;
        CommonResult commonResult = null;
        try {
            //1. 获取购物车信息
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(bookConstants.CARTSESSIONKEY);

            //2. 我们要响应给客户端的是{"totalCount":总条数,"totalAmount":总金额,"cartItemList":购物项的集合}
            responseMap = new HashMap<>();
            responseMap.put("totalCount", cart.getTotalCount());
            responseMap.put("totalAmount", cart.getTotalAmount());

            //获取购物项列表
            Collection<CartItem> cartItemCollection = cart.getCartItemMap().values();
            List<CartItem> cartItemList = new ArrayList<>(cartItemCollection);

            responseMap.put("cartItemList", cartItemList);

            //查询成功
            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error().setMessage("查询购物车信息失败");
        }

        //3. 将responseMap转成json并且响应给客户端
        JsonUtils.writeResult(response, commonResult);
    }

}
