package dao;

import bean.User;

import java.sql.SQLException;


    public interface UserDao {
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
