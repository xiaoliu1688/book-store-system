package dao;

import utils.PoolConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 21 : 22
 */
public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();

    public int update(String sql,Object... param) throws SQLException {
        int i = runner.update(PoolConnectionUtils.GetConnectionDruid(), sql, param);
        return  i;
    }

    public T getBean(Class<T> clazz,String sql,Object... param) throws SQLException {
        T query = runner.query(PoolConnectionUtils.GetConnectionDruid(), sql,new BeanHandler<>(clazz), param);
        return query;
    }
}