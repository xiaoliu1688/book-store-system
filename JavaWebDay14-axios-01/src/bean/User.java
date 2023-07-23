package bean;

/**
 * @author 刘翰林
 * @create 2023-01-05 22:20
 */
public class User {
    private Integer id;
    private String userName;
    private String nickName;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(Integer id, String userName, String nickName, String password) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
    }
}
