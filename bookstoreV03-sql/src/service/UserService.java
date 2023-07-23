package service;

import bean.User;

import java.sql.SQLException;


public interface UserService {
    /**
     * 注册用户
     * @param user
     * @throws SQLException
     */
    void doRegister(User user) throws SQLException;

    /**
     * 用户登录
     * @return
     * @throws Exception
     */
    User doLogin(User parameterUser) throws Exception;
}
