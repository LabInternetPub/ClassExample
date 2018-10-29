package cat.tecnocampus.webclassexample.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getAllNotes").authenticated()
                .antMatchers("/getNote/**").fullyAuthenticated()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/byebye.html").permitAll()
                .antMatchers("/newNote").hasRole("USER")
                .antMatchers("/getNotes/{userId}").access("authentication.name == #userId") //cannot use principal.username because when user is not logged in principal does not exist
                //.antMatchers("/getNotes/{userId}/createNote").access("@webSecurity.checkUserId(authentication,#userId)  and isFullyAuthenticated()")
                .antMatchers("/images/**").permitAll()
                .antMatchers("/api/getAllNotes/**").authenticated()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin() //to use forms (web)
                .and()
            .httpBasic() //to use http headers REST
                .and()
            .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("tecnocampus")
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //needed only when csrf is enabled (as by default is post)
                .logoutSuccessUrl("/byebye.html"); //where to go when logout is successful
                //.logoutUrl("logoutpage"); // default is "/logout""

            http
                    .csrf().disable()
                    .headers()
                    .frameOptions().disable();


    }
}
