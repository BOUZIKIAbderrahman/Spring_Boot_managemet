package management.system.sevice.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import management.system.exception.ResourceNotFoundException;
import management.system.model.User;
import management.system.repository.UserRepository;
import management.system.sevice.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User saveUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {

		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		userRepository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
	}

	@Override
	public String signUpUser(User user) {
		boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
		if (userExist) {
			throw new IllegalStateException("Email already taken");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		// Send configuration token
		return "It works";
	}

}
