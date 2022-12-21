package management.system.sevice.impl;

import lombok.AllArgsConstructor;
import management.system.config.EmailValidator;
import management.system.enumm.UserRole;
import management.system.model.User;
import management.system.model.UserRegistrationRequest;
import management.system.sevice.UserRegistrationService;
import management.system.sevice.UserService;

@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
	private final UserService userService;
	private final EmailValidator emailValidator;

	@Override
	public String register(UserRegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			return "Email not valid";
		}
		return userService.signUpUser(new User(request.getFirstName(), request.getLastName(), request.getPassword(),
				UserRole.USER, request.getEmail()));
	}

}
