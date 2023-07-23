package service.impl;

import bean.User;
import dao.impl.UserDaoImpl;
import service.UserService;
import utils.MD5Util;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public void doRegister(User user) throws SQLException {
        //1. 注册之前:校验用户名是否已存在
        //调用持久层的方法，根据username查找用户，查找到了就表示已存在
        User user1 = userDao.findByUsername(user.getUsername());
        if(user1 != null){
            throw new RuntimeException("用户已存在");
        }


        //2. 需求: 注册的时候，要对用户的密码进行加密
        String oldUserPwd = user.getUserPwd();
        //将明文进行加密得到加密后的密码
        String encodedPwd = MD5Util.encode(oldUserPwd);
        //将加密后的密码设置到user中
        user.setUserPwd(encodedPwd);

        //3. 处理注册，其实就是调用持久层的方法添加用户

        userDao.addUser(user);
    }

    @Override
    public User doLogin(User parameterUser) throws Exception {
        //1、判断用户名是否存在
        User user1 = userDao.findByUsername(parameterUser.getUsername());
        if(user1 != null){  //说明存在开始校验密码
            //由于user1的密码是经过加密后的密码所以需要将parameterUser的密码进行同样的加密来对比
            String encode = MD5Util.encode(parameterUser.getUserPwd());
            if(encode.equals(user1.getUserPwd())){
                return user1;   //说明用户名和密码都正确返回用户
            }
            else {
                throw new RuntimeException("登陆密码错误");
                        //说明密码错误
            }
        }
        throw  new RuntimeException("用户不存在");
    }
}