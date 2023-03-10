package management.system.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegistrationRequest {
	private final String firstName;
	private final String lastName;
	private final String userName;
	private final String password;
	private final String email;
}
