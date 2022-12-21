package management.system.sevice;

import java.util.List;

import management.system.model.User;

public interface UserService  {
	
	User saveUser(User user);

	List<User> getAllUsers();

	User getUserById(long id);

	User updateUser(User user);

	void deleteUserById(long id);
	
	public String signUpUser(User user);
}
