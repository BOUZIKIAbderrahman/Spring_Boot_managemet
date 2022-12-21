package management.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import management.system.model.UserRegistrationRequest;
import management.system.sevice.UserRegistrationService;

@RestController
@RequestMapping(value = "registration")
public class UserRegistrationController {
	private UserRegistrationService registrationService;

	@PostMapping
	public String register(@RequestBody UserRegistrationRequest request) {
		return registrationService.register(request);
	}
}
