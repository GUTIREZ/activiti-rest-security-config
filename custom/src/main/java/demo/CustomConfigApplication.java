package demo;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.boot.RestApiAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(exclude = RestApiAutoConfiguration.class)
@EnableWebSecurity
@ComponentScan({ "org.activiti.rest.exception", "org.activiti.rest.service.api" })
public class CustomConfigApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(CustomConfigApplication.class, args);
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

	@Bean
	public RestResponseFactory restResponseFactory() {
		return new RestResponseFactory();
	}

	@Bean
	public ContentTypeResolver contentTypeResolver() {
		return new DefaultContentTypeResolver();
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

}
