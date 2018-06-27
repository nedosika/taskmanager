package sample;

import java.sql.*;

public class DatabaseHandler extends Config{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:mysql://" + dbHost  + ":" + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void signUpUser(User user){
        String queryString = "INSERT INTO users (firstName, lastName, login, password, phone, email, gender) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(queryString)) {
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getPhone());
            prSt.setString(6, user.getEmail());
            prSt.setString(7, user.getGender());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        String queryString = "SELECT * FROM users WHERE login = ? AND password = ?";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(queryString)) {
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resultSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
