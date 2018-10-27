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
                .withUser("alvarez").password("{bcrypt}$2a$10$7PFxXn4TQRiut9jNcAl7AubQZUWWck/eML3TDaQtoZiWNEN6o.Ig6").roles("USER").and()
                //.withUser("roure").password("{bcrypt}$2a$10$0VGzG8lfiDXBnFTE98lfiOLtP4uh62wnE6iWs5.2AMrJ3G9k7XZqu").roles("ADMIN").and()
                .withUser("roure").password("{noop}roure").roles("ADMIN").and()
                .withUser("lecina").password("{bcrypt}$2a$10$8J6t9dJ7KCU1KGBd7E2/2enlG1tyNkzoswFbckO01Efwp6wF6MH7u").roles("USER,ADMIN");
    }

}
