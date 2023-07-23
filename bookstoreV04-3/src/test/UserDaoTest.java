package test;

import bean.User;
import dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author:刘翰林
 * @Description:
 * @Date: 25/9/2022 20 : 44
 */
public class UserDaoTest {
    @Test
    public void test1() throws SQLException {
        User user = new User(null,"asdf林","1asdf","asdf98291@qq.com");
        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.addUser(user);
        User byUsername = userDao.findByUsername("asdf林");
        System.out.println(byUsername);
    }
}