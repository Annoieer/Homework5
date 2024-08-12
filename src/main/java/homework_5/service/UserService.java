package homework_5.service;

import homework_5.dto.UserDto;
import homework_5.dto.UserResponseDto;
import homework_5.entity.mapper.UserMapper;
import homework_5.entity.user.IUserDao;
import homework_5.exceptions.CustomException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserMapper userMapper;

    private final IUserDao iUserDao;
    private final ArrayList<UserDto> users = new ArrayList<>();

    public UserService(UserMapper userMapper, IUserDao iUserDao) {
        this.userMapper = userMapper;
        this.iUserDao = iUserDao;
    }

    @PostConstruct
    private void init() {
        iUserDao.getAllUsers()
                .forEach(user -> users.add(userMapper.toDto(user)));
    }

    public UserDto getUser(Long id) {
        try {
            return userMapper.toDto(iUserDao.getUserById(id));
        } catch (NullPointerException exception) {
            throw new CustomException("Пользователь с id = " + id + " не существует", HttpStatus.NOT_FOUND);
        }
    }

    public UserResponseDto getAllUsers() {
        return new UserResponseDto(users.size(), users);
    }
}
