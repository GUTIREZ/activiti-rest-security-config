package demo;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.spring.boot.RestApiAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(exclude = {
		RestApiAutoConfiguration.SecurityConfiguration.class // this exclude does not work
//		RestApiAutoConfiguration.class // this exclude works
})
public class DemoApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.httpBasic();
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
			}

		};
	}

}
