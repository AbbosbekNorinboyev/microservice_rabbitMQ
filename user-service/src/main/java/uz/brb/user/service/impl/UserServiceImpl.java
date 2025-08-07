package uz.brb.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.brb.user.dto.UserDto;
import uz.brb.user.entity.Users;
import uz.brb.user.mapper.UserMapper;
import uz.brb.user.repository.UsersRepository;
import uz.brb.user.service.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<?> create(UserDto userDto) {
        Users user = userMapper.toEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(usersRepository.save(user));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("USER NOT FOUND");
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @Override
    public boolean existsById(Long id) {
        return usersRepository.existsById(id);
    }
}
