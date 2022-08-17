package bookers.bookkeeper.user;

import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.user.dto.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

import static bookers.bookkeeper.security.SecurityConfig.passwordEncoder;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {

    private static final String DEFAULT_AUTHORITY = "USER";

    @Autowired
    public UserService(UserRepository rep) {
        super(rep);
    }

    public User updateUser(String userName, LoginHelper updateData) {
        User foundUser = getUserByNameWithCheck(userName);
        setFieldIfNotNull(updateData.getUsername(), foundUser::setUsername);
        setFieldIfNotNull(updateData.getPassword(), (pass) -> foundUser.setPassword(passwordEncoder().encode(pass)));
        setFieldIfNotNull(updateData.getImage(), foundUser::setImage);
        setFieldIfNotNull(updateData.getEmail(), foundUser::setImage);

        rep.save(foundUser);
        return foundUser;
    }

    public <T> void setFieldIfNotNull(T toCheck, Consumer<T> consumer) {
        if (Objects.nonNull(toCheck)) {
            consumer.accept(toCheck);
        }
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
        user.setImage(registerData.getImage());
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
