package cat.tecnocampus.webclassexample.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("security_memory")
@EnableWebSecurity
public class MemorySecurityConfiguration extends BaseSecurityConfiguration {

    //The {noop} is used in Spring Security 5.0. It substitutes the NoOpPasswordEncoder, an encoder that does nothing
    //There are other encoders that encrypt using other algorithms
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER").and()
                .withUser("roure").password("{noop}roure").roles("ADMIN").and()
                .withUser("both").password("{noop}both").roles("USER,ADMIN");
    }

}
