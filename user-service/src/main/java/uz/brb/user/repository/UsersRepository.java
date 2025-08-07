package uz.brb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.brb.user.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsById(Long id);
}