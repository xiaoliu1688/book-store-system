package dao;

import bean.User;

import java.sql.SQLException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 22 : 25
 */public interface UserDao {
    /**
     * 添加用户
     * @param username
     * @throws SQLException
     */
    User findByUsername(String username) throws SQLException;
    /**
     * 查询数据
     * @param user
     * @throws SQLException
     */
    void addUser(User user) throws SQLException;
}
