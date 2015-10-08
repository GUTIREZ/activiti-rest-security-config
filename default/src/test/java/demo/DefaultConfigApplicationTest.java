package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfigApplication.class)
public class DefaultConfigApplicationTest {

	@Autowired
	private WebSecurityConfigurerAdapter webSecurity;

	@Test
	public void test() throws Exception {
		AuthenticationManager authenticationManager = webSecurity.authenticationManagerBean();
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));

		assertThat(authentication.getPrincipal(), instanceOf(String.class));
		assertThat(authentication.getAuthorities().iterator().next(), instanceOf(SimpleGrantedAuthority.class));
	}

}
