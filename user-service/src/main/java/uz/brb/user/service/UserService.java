package uz.brb.user.service;

import org.springframework.http.ResponseEntity;
import uz.brb.user.dto.UserDto;

public interface UserService {
    ResponseEntity<?> create(UserDto userDto);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    boolean existsById(Long id);
}
