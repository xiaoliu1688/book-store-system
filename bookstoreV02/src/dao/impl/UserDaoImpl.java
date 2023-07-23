package dao.impl;

import bean.User;
import dao.BaseDao;
import dao.UserDao;

import java.sql.SQLException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 20 : 32
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User findByUsername( String username) throws SQLException {


        String sql = "select user_id userId,user_name username,user_pwd userPwd,email from bookstore where user_name = ?";
        User u = getBean(User.class, sql, username);
        return u;
    }


    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into bookstore (user_name,user_pwd,email) values(?,?,?)";
        int update = update(sql, user.getUsername(),user.getUserPwd(),user.getEmail());
    }
}