package bookers.bookkeeper.user;

import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.security.SecurityConfig;
import bookers.bookkeeper.user.dto.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import static bookers.bookkeeper.security.SecurityConfig.passwordEncoder;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {

    private static final String DEFAULT_AUTHORITY = "USER";

    @Autowired
    public UserService(UserRepository rep) {
        super(rep);
    }

    public User updateUser(String userName, LoginHelper user) {
        User foundUser = getUserByNameWithCheck(userName);
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(passwordEncoder().encode(user.getPassword()));
        if (user.getEmail() != null) {
            foundUser.setEmail(user.getEmail());
        }
        rep.save(foundUser);
        return foundUser;
    }

    public User getUserByNameWithCheck(String userName) {
        Optional<User> found = rep.findUserByUsername(userName);
        if (found.isEmpty()) {
            throw new UsernameNotFoundException("User to update not found");
        }
        return found.get();
    }

    public User registerUser(LoginHelper registerData) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        User user = new User();
        authorities.add(new SimpleGrantedAuthority(DEFAULT_AUTHORITY));
        user.setUsername(registerData.getUsername());
        user.setEmail(registerData.getEmail());
        user.setUserlist(new ArrayList<>());
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder().encode(registerData.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setIsCredentialsNonExpired(true);

        return rep.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByNameWithCheck(username);
    }

}
