# Activiti REST - Spring Security Configuration

This is a minimal project that demonstrates how to configure ```activiti-spring-boot-starter-rest-api``` based application to use ```IdentityServiceUserDetailsService``` backed ```AuthenticationProvider```.

By default, Activiti REST interface is secured by configuration in ```RestApiAutoConfiguration.SecurityConfiguration``` which defines an instance of ```BasicAuthenticationProvider``` as ```AuthenticationProvider```. ```BasicAuthenticationProvider``` returns ```Authentication``` objects populated with a simple username ```String``` as principal and list of ```SimpleGrantedAuthority``` objects as authorities.

```activiti-spring-boot-starter-rest-api``` also provides ```IdentityServiceUserDetailsService``` bean (configured in ```SecurityAutoConfiguration```) but previously mentioned configuration does not make use of it. This means that custom security configuration is required - however ```RestApiAutoConfiguration.SecurityConfiguration``` class is always registered together with its parent class by virtue of being a nested ```@Configuration``` class and sharing the same ```@ConditionalOnClass``` conditions. That makes ```SecurityConfiguration``` impossible to replace without replacing the entire ```RestApiAutoConfiguration```.

Module ```default``` demonstrates default security setup using ```DefaultConfigApplication```, accompanied by ```DefaultConfigApplicationTest```.

Module ```custom``` demonstrates customized security setup which makes use of ```IdentityServiceUserDetailsService``` using ```CustomConfigApplication```, accompanied by ```CustomConfigApplicationTest```.
