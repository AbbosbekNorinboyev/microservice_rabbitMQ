package uz.brb.user.mapper;

import org.springframework.stereotype.Component;
import uz.brb.user.dto.UserDto;
import uz.brb.user.entity.Users;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public Users toEntity(UserDto userDto) {
        return Users.builder()
                .fullName(userDto.getFullName())
                .build();
    }

    public UserDto toDto(Users user) {
        return UserDto.builder()
//                .id(user.getId())
                .fullName(user.getFullName())
                .build();
    }

    public List<UserDto> dtoList(List<Users> list) {
        if (list != null && !list.isEmpty()) {
            return list.stream().map(this::toDto).toList();
        }
        return new ArrayList<>();
    }
}
