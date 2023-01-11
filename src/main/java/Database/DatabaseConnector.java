package Database;

import model.User;

import java.sql.*;

public class DatabaseConnector {
    public void getUsers(){
        Statement statement = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securite-tp","root","");
        } catch(Exception e){
            System.out.println(e);
        }

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT name, firstname, age, mail, password FROM users");

            while(rs.next()){
                String name = rs.getString("name");
                String firstname = rs.getString("firstname");
                String age = rs.getString("age");

                System.out.println(name + " " + firstname + " : " + age + " ans");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getUserByLogin(String mail, String password){
        Statement statement = null;
        Connection conn = null;
        ResultSet rs = null;

        String name = "", firstname = "";
        int id = 0, age = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securite-tp","root","");
        } catch(Exception e){
            System.out.println(e);
        }

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT id, name, firstname, age, mail, password FROM users WHERE mail = '" + mail + "' AND password = '" + password + "'");

            while(rs.next()){
                name = rs.getString("name");
                firstname = rs.getString("firstname");
                age = Integer.parseInt(rs.getString("age"));
                id = Integer.parseInt(rs.getString("id"));

                return new User(id,name,firstname,age,mail,password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new User(id,name,firstname,age,mail,password);
    }

    public User getUserById(String id){
        PreparedStatement statement = null;
        Connection conn = null;
//        ResultSet rs = null;

        String name = "", firstname = "", password = "", mail = "";
        int age = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            //?allowMultiQueries=true
            Connection connection = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securite-tp","root","");
        } catch(Exception e){
            System.out.println(e);
        }

        try {
//            rs = statement.executeQuery("SELECT id, name, firstname, age, mail, password FROM users WHERE id = '" + id + "'");
            statement = conn.prepareStatement("SELECT id,name,firstname,age,mail,password FROM users WHERE id = ?");
            statement.setString(1,id);

            ResultSet rs = statement.executeQuery();

            if(rs == null){
                System.out.println("rs is null");
            }

            while(rs.next()){
                name = rs.getString("name");
                firstname = rs.getString("firstname");
                age = rs.getInt("age");
                mail = rs.getString("mail");
                password = rs.getString("password");
                System.out.println(password);
            }
        } catch (SQLException e) {
            System.out.println("CATCH");
            System.out.println(e);
        } finally {
            try {
                conn.close();
                return new User(0,name,firstname,age,mail,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createUser(User user){
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securite-tp","root","");
        } catch(Exception e){
            System.out.println(e);
        }

        try {
            statement = conn.prepareStatement("INSERT INTO users(name,firstname,age,mail,password) VALUES(?,?,?,?,?)");
            statement.setString(1,user.getName());
            statement.setString(2,user.getFirstname());
            statement.setInt(3,user.getAge());
            statement.setString(4,user.getMail());
            statement.setString(5,user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
