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
import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {


    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public Optional<User> findUserByName(String name) {
        return getTrep().findUserByName(name);
    }


    public User updateUser(String userName, LoginHelper user) {
        Optional<User> found = findUserByName(userName);
        if (found.isEmpty()) {
            throw new UsernameNotFoundException("User to update not found");
        }
        User foundUser = found.get();
        foundUser.setName(user.getUsername());
        foundUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        foundUser.setEmail(user.getEmail());
        super.save(foundUser);
        return foundUser;
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

        return super.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> op = findUserByName(username);
        if (op.isPresent()) {
            return op.get();
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
