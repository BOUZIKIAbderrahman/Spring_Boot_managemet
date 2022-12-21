package management.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import management.system.model.User;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
