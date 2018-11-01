package cat.tecnocampus.webclassexample.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return findUserbyUername(username);

    }

    private UserDetails findUserbyUername(String username) {
        final String USERS_QUERY = "select username, password, enabled from user_lab where username = ?";

        final String AUTHORITIES_QUERY = "select username, role from authorities where username = ?";

        RowMapper<User.UserBuilder> mapper = (rs, i) -> {
            User.UserBuilder builder = null;
            builder = org.springframework.security.core.userdetails.User.withUsername(rs.getString("username"));
            builder.password(rs.getString("password"));

            System.out.println("UserDetailsServiceImpl, username: " + rs.getString("username"));

            return builder;
        };

        try {
            List<GrantedAuthority> roles = jdbcTemplate.query(AUTHORITIES_QUERY, new Object[]{username},
                    (rs, i) -> {return new SimpleGrantedAuthority(rs.getString("role"));});

            User.UserBuilder builder = jdbcTemplate.queryForObject(USERS_QUERY, new Object[]{username}, mapper);

            builder.authorities(roles);
            return builder.build();

        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new UsernameNotFoundException("This user does not exist");
        }
    }
}
