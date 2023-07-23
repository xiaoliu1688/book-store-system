package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.PoolConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


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

    public List<T> getList(Class<T> clazz,String sql,Object... param) throws SQLException {
        List<T> list = runner.query(PoolConnectionUtils.GetConnectionDruid(),sql,new BeanListHandler<>(clazz),param);
        return list;
    }

    //用于查询特殊值的方法，如： select count(*) from user_table;
    public <E> E getValue(String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;

        try {
            conn = PoolConnectionUtils.GetConnectionDruid();
            ps = conn.prepareStatement(sql);

            for(int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return (E)resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}