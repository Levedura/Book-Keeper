package bookers.bookkeeper.user;

import bookers.bookkeeper.generics.BaseController;
import bookers.bookkeeper.user.dto.LoginHelper;
import bookers.bookkeeper.user.dto.UserDTO;
import bookers.bookkeeper.userprofile.UserProfileModelAssembler;
import bookers.bookkeeper.userprofile.UserProfileService;
import bookers.bookkeeper.userprofile.dto.UserProfileDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, UserDTO, UserService, UserModelAssembler> {

    private final UserProfileService userProfileService;
    private final UserProfileModelAssembler profileAssembler;

    public UserController(UserService service, UserModelAssembler modelAssembler, UserProfileService userProfileService, UserProfileModelAssembler profileAssembler) {
        super(service, modelAssembler);
        this.userProfileService = userProfileService;
        this.profileAssembler = profileAssembler;
    }

    @PutMapping(value = ("/{username}/update"))
    @PreAuthorize("authentication.name == #userName")
    public EntityModel<UserDTO> updateUser(@RequestParam String userName, @RequestBody LoginHelper user) {
        return modelAssembler.toModel(service.updateUser(userName, user));
    }


    //Related to user profile

    @GetMapping("/{userName}/profile")
    public EntityModel<UserProfileDTO> getUserProfile(@PathVariable String userName) {
        return profileAssembler.toModel(userProfileService.getAndUpdate(userName));
    }

    @PutMapping(value = "/{userName}/profile", params = "bookId")
    public EntityModel<UserProfileDTO> addFavoriteBook(@PathVariable String userName, @RequestParam Long bookId) {
        return profileAssembler.toModel(userProfileService.addFavoriteBook(userName, bookId));
    }

    @PatchMapping(value = "/{userName}/profile", params = "bookId")
    public EntityModel<UserProfileDTO> removeFavoriteBook(@PathVariable String userName, @RequestParam Long bookId) {
        return profileAssembler.toModel(userProfileService.removeFavoriteBook(userName, bookId));
    }

    @PutMapping(value = "/{userName}/profile", params = "authorId")
    public EntityModel<UserProfileDTO> addFavoriteAuthor(@PathVariable String userName, @RequestParam Long authorId) {
        return profileAssembler.toModel(userProfileService.addFavoriteAuthor(userName, authorId));
    }

    @PatchMapping(value = "/{userName}/profile", params = "authorId")
    public EntityModel<UserProfileDTO> removeFavoriteAuthor(@PathVariable String userName, @RequestParam Long authorId) {
        return profileAssembler.toModel(userProfileService.removeFavoriteAuthor(userName, authorId));
    }
}
