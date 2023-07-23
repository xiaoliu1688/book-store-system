package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 18/8/2022 21 : 11
 */
public class PoolConnectionUtils {
    //*********************************
    //此处只能用静态代码块将连接池的初始化工作和创建在静态代码块中进行
    private static DataSource dataSource =null;
    static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        try {
            InputStream ism = PoolConnectionUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties pro = new Properties();
            pro.load(ism);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection GetConnectionDruid() {
        try {
            Connection connection = threadLocal.get();
            if(connection == null){
                connection = dataSource.getConnection();
                threadLocal.set(connection);
                return connection;
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public static void releaseConnection(){
        try {
            //将链接归还回连接池
            GetConnectionDruid().close();
            threadLocal.remove();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}