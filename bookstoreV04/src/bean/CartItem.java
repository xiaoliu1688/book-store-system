package bean;

import java.math.BigDecimal;

/**
 * @author 刘翰林
 * @create 2022-11-18 23:19
 */
public class CartItem {
    private Integer bookId;
    private String bookName;
    private String imgPath;
    /**
     * 商品的单价
     */
    private Double price;
    private Integer count;
    /**
     * 购物项的金额
     */
    private Double amount;

    public CartItem() {
    }

    public CartItem(Integer bookId, String bookName, String imgPath, Double price, Integer count, Double amount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.imgPath = imgPath;
        this.price = price;
        this.count = count;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cartltem{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public Double getAmount() {
        //1. 将price和count封装成BigDecimal类型
        BigDecimal bigDecimalPrice = new BigDecimal(price + "");
        BigDecimal bigDecimalCount = new BigDecimal(count + "");

        //2. 使用bigDecimal的方法进行乘法,然后再转化为double类型
        return bigDecimalCount.multiply(bigDecimalPrice).doubleValue();
    }
    /*
        数量+1
     */
    public void CountIncrease(){
        this.count ++;
    }

    /*
        数量-1
     */
    public void CountDecrease(){
        this.count --;
    }

}
