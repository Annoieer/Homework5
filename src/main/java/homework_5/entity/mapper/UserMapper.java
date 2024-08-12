package homework_5.entity.mapper;

import homework_5.dto.UserDto;
import homework_5.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
