package constants;/**
@author 刘翰林
@create 2023-01-01 20:33
*/public class bookConstants {
    public static final String USERSESSIONKEY = "loginUser";
    public static final String CARTSESSIONKEY = "cart";

    /**
     * 未发货订单
     */
    public static final Integer UNFILLEDORDER = 0;

    /**
     * 已发货订单
     */
    public static final Integer FILLEDORDER = 1;

    /**
     * 已确认收货订单
     */
    public static final Integer RECEIVEDORDER = 2;

}
