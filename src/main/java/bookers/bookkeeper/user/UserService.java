package bookers.bookkeeper.user;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {


    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User updateUser(String userName, LoginHelper user) {
        User foundUser = getUserByNameWithCheck(userName);
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.getEmail() != null) {
            foundUser.setEmail(user.getEmail());
        }
        saveUser(foundUser);
        return foundUser;
    }

    public User getUserByNameWithCheck(String userName) {
        Optional<User> found = rep.findUserByUsername(userName);
        if (found.isEmpty()) {
            throw new UsernameNotFoundException("User to update not found");
        }
        return found.get();
    }

    public User saveUser(User user) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        user.setAuthorities(authorities);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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

    public List<User> getUsers() {
        return rep.findAll();
    }
}
