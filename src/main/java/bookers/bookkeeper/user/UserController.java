package bookers.bookkeeper.user;

import bookers.bookkeeper.generics.BaseController;
import bookers.bookkeeper.user.dto.LoginHelper;
import bookers.bookkeeper.user.dto.UserDTO;
import bookers.bookkeeper.user.dto.UserDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserDTO, UserDTOConverter, UserService> {

    AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService service, UserDTOConverter converter, AuthenticationManager authenticationManager) {
        super(service, converter);
        this.authenticationManager = authenticationManager;
    }

    @PutMapping("/{username}")
    @PreAuthorize("authentication.name == #userName")
    public UserDTO updateUser(@PathVariable(name = "username") String userName, @RequestBody LoginHelper user) {
        return converter.toDto(service.updateUser(userName, user));
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginHelper credentials) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return "Logged In ";
        }
        return "Could not authenticate user.";
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody LoginHelper user) {
        return converter.toDto(service.registerUser(user));
    }
}
