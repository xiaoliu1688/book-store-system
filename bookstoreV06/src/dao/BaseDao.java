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

    /**
     * 批处理方法
     * @date
     * @author
     * @return
     * @throws
     */
    public int[] batchUpdate(String sql,Object[][] paramArr) {
        Connection connection = null;
        try {
            connection = PoolConnectionUtils.GetConnectionDruid();
            return runner.batch(connection,sql,paramArr);//批处理sql语句
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public int update(String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        int i = runner.update(connection, sql, param);
        return  i;
    }

    public T getBean(Class<T> clazz,String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        T query = runner.query(connection, sql,new BeanHandler<>(clazz), param);
        return query;
    }

    public List<T> getList(Class<T> clazz,String sql,Object... param) throws SQLException {
        Connection connection = PoolConnectionUtils.GetConnectionDruid();
        List<T> list = runner.query(connection,sql,new BeanListHandler<>(clazz),param);
        return list;
    }


}