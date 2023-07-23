package dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.PoolConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 21 : 22
 */
public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();

    public int update(String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        int i = runner.update(connection, sql, param);
        releaseConnection(connection);
        return  i;
    }

    public T getBean(Class<T> clazz,String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        T query = runner.query(connection, sql,new BeanHandler<>(clazz), param);
        releaseConnection(connection);
        return query;
    }

    public List<T> getList(Class<T> clazz,String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        List<T> list = runner.query(connection,sql,new BeanListHandler<>(clazz),param);
        releaseConnection(connection);
        return list;
    }
    public static void releaseConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}