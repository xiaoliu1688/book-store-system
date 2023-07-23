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

    public static Connection GetConnectionDruid() throws SQLException {

        Connection connection = dataSource.getConnection();
        return connection;
    }
}