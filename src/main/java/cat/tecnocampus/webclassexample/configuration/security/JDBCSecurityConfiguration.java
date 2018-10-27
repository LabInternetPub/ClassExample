package cat.tecnocampus.webclassexample.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Profile("security_jdbc")
@EnableWebSecurity
public class JDBCSecurityConfiguration extends BaseSecurityConfiguration {
    private DataSource dataSource;

    private static final String USERS_QUERY = "select username, password, enabled from user_lab where username = ?";

    private static final String AUTHORITIES_QUERY = "select username, role from authorities where username = ?";

    public JDBCSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //Configure authentication manager
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY);
    }

}
