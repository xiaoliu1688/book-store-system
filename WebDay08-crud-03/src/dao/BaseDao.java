package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.PoolConnectionUtils;

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
        int i = runner.update(PoolConnectionUtils.GetConnectionDruid(), sql, param);
        return  i;
    }

    public T getBean(Class<T> clazz,String sql,Object... param) throws SQLException {
        System.out.println("sdfasdf");
        T query = runner.query(PoolConnectionUtils.GetConnectionDruid(), sql,new BeanHandler<>(clazz), param);
        return query;
    }

    public List<T> getBeanList(Class<T> clazz,String sql,Object... param) throws SQLException {
        return runner.query(PoolConnectionUtils.GetConnectionDruid(), sql,new BeanListHandler<>(clazz), param);
    }
}