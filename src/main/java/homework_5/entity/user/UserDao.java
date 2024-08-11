package homework_5.entity.user;

import homework_5.config.DataSourceConfig;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class UserDao extends DataSourceConfig implements IUserDao {
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id=?;";
    private static final String READ_ALL_USER_QUERY = "SELECT * FROM users;";

    public User getUserById(@NotNull Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_USER_QUERY)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            ArrayList<User> users = getUsersFromResultSet(resultSet);
            if (users.size() != 0) {
                return users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("В таблице нет пользователя с id = " + id);
        return null;
    }

    public ArrayList<User> getAllUsers() {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_ALL_USER_QUERY)) {
            ResultSet resultSet = pst.executeQuery();
            ArrayList<User> users = getUsersFromResultSet(resultSet);
            if (users.size() != 0) {
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка получения списка пользователей");
        return null;
    }

    private ArrayList<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<User> usersById = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            User user = new User(id, username);
            usersById.add(user);
        }
        return usersById;
    }

}
