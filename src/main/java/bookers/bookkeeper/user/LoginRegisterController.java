package bookers.bookkeeper.user;

import bookers.bookkeeper.user.dto.LoginHelper;
import bookers.bookkeeper.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegisterController {

    private final AuthenticationManager authenticationManager;
    private final UserModelAssembler userAssembler;
    private final UserService userService;

    @Autowired
    public LoginRegisterController(AuthenticationManager authenticationManager, UserModelAssembler userAssembler, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userAssembler = userAssembler;
        this.userService = userService;
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody LoginHelper credentials) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return "Logged In";
        }

        return "Could not authenticate user.";
    }

    @PostMapping("/register")
    public EntityModel<UserDTO> registerUser(@RequestBody LoginHelper user) {
        return userAssembler.toModel(userService.registerUser(user));
    }

}
