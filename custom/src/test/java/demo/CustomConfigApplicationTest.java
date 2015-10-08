package demo;

import org.activiti.spring.security.IdentityServiceUserDetailsService.GroupGrantedAuthority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomConfigApplication.class)
public class CustomConfigApplicationTest {

	@Autowired
	private WebSecurityConfigurerAdapter webSecurity;

	@Test
	public void test() throws Exception {
		AuthenticationManager authenticationManager = webSecurity.authenticationManagerBean();
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));

		assertThat(authentication.getPrincipal(), instanceOf(User.class));
		assertThat(authentication.getAuthorities().iterator().next(), instanceOf(GroupGrantedAuthority.class));
	}

}
