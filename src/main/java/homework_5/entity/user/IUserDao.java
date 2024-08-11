package homework_5.entity.user;

import java.util.ArrayList;

public interface IUserDao {

    User getUserById(Long id);

    ArrayList<User> getAllUsers();
}
