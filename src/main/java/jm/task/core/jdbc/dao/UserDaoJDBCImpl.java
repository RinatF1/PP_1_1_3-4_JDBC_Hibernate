package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


//Util util = new Util();

 public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        String query = "CREATE TABLE IF NOT EXISTS db1.users " +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT(3))";
       try (Statement statement = Util.getConnection().createStatement()) {
          statement.executeUpdate(query);
          System.out.println("Таблица создана");
       } catch (SQLException e) {
          throw new RuntimeException(e);
       }

    }

    public void dropUsersTable() {
    String query1 = "DROP TABLE IF EXISTS db1.users ";
       try (Statement statement = Util.getConnection().createStatement()) {
          statement.executeUpdate(query1);
          System.out.println("Таблица удалена");
       } catch (SQLException e) {
          throw new RuntimeException(e);
       }

    }

    public void saveUser(String name, String lastName, byte age) {
    String insertNew = "INSERT INTO db1.users (name, lastName, age) VALUES (?, ?, ?)";
      try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(insertNew)) {
         preparedStatement.setString(1, name);
         preparedStatement.setString(2, lastName);
         preparedStatement.setInt(3, age);

         preparedStatement.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }

    }

    public void removeUserById(long id) {
    String delete = "DELETE FROM db1.users WHERE ID = ?";
    try (PreparedStatement deleteID = Util.getConnection().prepareStatement(delete)) {
       deleteID.setLong(1, id);

       deleteID.executeUpdate();
    } catch (SQLException e) {
       throw new RuntimeException(e);
    }

    }

    public List<User> getAllUsers() {
    String select = "SELECT id, name, lastName, age FROM db1.users";
    List<User> users = new ArrayList<>();

      try (ResultSet resultSet = Util.getConnection().createStatement().executeQuery(select)) {
        while (resultSet.next()) {
           User user = new User();
           user.setId(resultSet.getLong("id"));
           user.setName(resultSet.getString("name"));
           user.setLastName(resultSet.getString("lastName"));
           user.setAge(resultSet.getByte("age"));

           users.add(user);
        }

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
       return users;
    }

    public void cleanUsersTable() {
    String clean = "DELETE FROM db1.users";
    try (Statement delete = Util.getConnection().createStatement()) {
       delete.executeUpdate(clean);
       System.out.println("Таблица очищена");

   } catch (SQLException e) {
      e.printStackTrace();
       System.out.println("Таблицу не удалось очистить");
   }

    }


}
