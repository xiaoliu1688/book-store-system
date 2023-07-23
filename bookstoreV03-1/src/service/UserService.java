package service;

import bean.User;

import java.sql.SQLException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 22 : 44
 */
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
