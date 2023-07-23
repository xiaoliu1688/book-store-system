package bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 刘翰林
 * @create 2022-11-18 23:22
 */
public class Cart {

    //建一个map用来存放购物项
    Map<Integer,CartItem> cartItemMap = new HashMap<>();

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemMap=" + cartItemMap +
                '}';
    }

    /**
     * 添加商品到购物车
     * @date
     * @author
     * @return
     * @throws
     */
    public void addBookToCart(Book book){
        //判断该书是否存在于购物车
        if(cartItemMap.containsKey(book.getBookId())){
            //如果该书已经存在购物车，则把该购物项的count+1，购物项的金额+1
            //count +1
            itemCountIncrease(book.getBookId());
            //金额+1
            //TODO
        }else {
            //如果该书不存在于购物项，则将该书添加到map中
            cartItemMap.put(book.getBookId(), new CartItem(book.getBookId(), book.getBookName(), book.getImgPath(), book.getPrice(), 1, book.getPrice()));
        }

    }

    /**
     * 显示购物车信息
     * @date
     * @author
     * @return
     * @throws
     */

    public Map<Integer,CartItem> getCartItemMap(){
        return cartItemMap;
    }

    /**
     * 购物车某个购物项数量+1
     * @date
     * @author
     * @return
     * @throws
     */
    public void itemCountIncrease(Integer bookId){
//        Cartltem cartltem = CartltemMap.get(book.getBookId());
//        cartltem.setCount(cartltem.getCount() + 1);
//        cartltem.setAmount(cartltem.getAmount() + book.getPrice());
//        CartltemMap.put(book.getBookId(), cartltem);

        cartItemMap.get(bookId).CountIncrease();
    }

    /**
     * 购物车某个购物项数量-1
     * @date
     * @author
     * @return
     * @throws
     */
    public void itemCountDecrease(Integer bookId){

        cartItemMap.get(bookId).CountDecrease();
        //如果数量-1后数量为0，则调用删除方法
        if(cartItemMap.get(bookId).getCount() == 0){
            //删除该购物项
            removeCartItem(bookId);
        }
    }

    /**
     * 从购物车移除购物项
     * @date
     * @author
     * @return
     * @throws
     */
    public void removeCartItem(Integer bookId){
        cartItemMap.remove(bookId);
    }

    /**
     * 修改购物项的数量
     * @date
     * @author
     * @return
     * @throws
     */
    public void updateItemCount(Integer bookId,Integer newCount){
        cartItemMap.get(bookId).setCount(newCount);
    }

    /**
     * 获取购物车中的购物项的总数
     * @return
     */
    public Integer getTotalCount(){
        //遍历出每一个CartItem的count然后累加
        //方式一: 采用JDK1.8的新特性
//        AtomicReference<Integer> totalCount = new AtomicReference<>(0);
//        cartItemMap.forEach((k,cartItem) -> {
//            totalCount.updateAndGet(v -> v + cartItem.getCount());
//        });
//
//        return totalCount.get();

//        方式二: 使用原生的entrySet遍历Map
        Integer counts = 0;
        Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();//拿到所有的键值对组成的集合
        for(Map.Entry<Integer,CartItem> entry : entries){
            counts += entry.getValue().getCount();
        }
        return counts;
    }

    /**
     * 获取购物车的总金额
     * @date
     * @author
     * @return
     * @throws
     */

    public Double getTotalAmount(){
        //解决精度问题的核心: 就是将要进行运算的数据转成BigDecimal类型之后再计算
        //声明一个总金额
        BigDecimal bigDecimalTotalAmount = new BigDecimal("0.0");
        for (Map.Entry<Integer, CartItem> cartItemEntry : cartItemMap.entrySet()) {
            //cartItemEntry.getValue().getAmount()是获取每一个购物项的金额

            //使用总金额加上遍历出来的购物项的金额
            bigDecimalTotalAmount = bigDecimalTotalAmount.add(new BigDecimal(cartItemEntry.getValue().getAmount() + ""));
        }
        return bigDecimalTotalAmount.doubleValue();
    }




}
