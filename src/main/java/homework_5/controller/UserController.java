package homework_5.controller;

import homework_5.dto.UserDto;
import homework_5.dto.UserResponseDto;
import homework_5.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public UserResponseDto getAllUsers() {
        ArrayList<UserDto> users = userService.getAllUsers();
        return new UserResponseDto(users.size(), users);
    }

    @GetMapping(value = "/{userId}")
    public UserDto getByUserId(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
