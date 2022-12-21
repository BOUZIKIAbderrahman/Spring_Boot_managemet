package management.system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import management.system.model.User;
import management.system.sevice.UserService;

@Controller
@RequestMapping(value = "users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// build get all employee REST API
	@GetMapping("/list")
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "user/userList";
	}

	@GetMapping("user/add")
	public String createUserForm(Model model) {
		// create user object to hold user form data
		User user = new User();
		model.addAttribute("user", user);
		return "user/addUser";
	}

	@PostMapping(value = "save")
	public String saveUser(@ModelAttribute("user") User user) {
		// save user in database
		userService.saveUser(user);
		return "redirect:/users/list";
	}

	@GetMapping(value = "user/edit/{id}")
	public String update(Model model, @PathVariable("id") long id) {
		model.addAttribute("user", userService.getUserById(id));
		return "user/updateUser";
	}

	@PostMapping(value = "update/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		User existingUser = userService.getUserById(id);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setRole(user.getRole());
		existingUser.setPassword(user.getPassword());
		userService.updateUser(existingUser);
		return "redirect:/users/list";
	}

	@GetMapping(value = "delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		userService.deleteUserById(id);
		return "redirect:/users/list";
	}

}
