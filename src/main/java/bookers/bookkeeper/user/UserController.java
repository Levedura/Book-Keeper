package bookers.bookkeeper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authManager) {
        this.userService = userService;
        this.authManager = authManager;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllEntities();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name = "id") Long userId) {
        return userService.findEntityById(userId);
    }

    @PutMapping("/user/{username}")
    @PreAuthorize(value = "authentication.principal.equals(#user.username)")
    public User updateUser(@PathVariable(name = "username") String userName, @RequestBody LoginHelper user){
        return userService.updateUser(userName,user);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.saveEntity(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginHelper credentials) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth = authManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return "Logged In :) ";
        }
        return "Could not authenticate user";
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
