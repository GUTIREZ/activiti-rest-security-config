package demo;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DefaultConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultConfigApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final IdentityService identityService) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				Group admins = identityService.newGroup("admins");
				admins.setType("security-role");
				identityService.saveGroup(admins);

				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				identityService.saveUser(admin);

				identityService.createMembership("admin", "admins");
			}

		};
	}

}
