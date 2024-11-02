package POJO;

public class User {
    int id;
    String username;
    String password;
    public  User(){}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //在mybatis传入User对象参数，获取各个属性通过get方法


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
