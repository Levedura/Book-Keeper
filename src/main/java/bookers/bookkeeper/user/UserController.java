package bookers.bookkeeper.user;

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
public class UserController {

    private final UserProfileService userProfileService;
    private final UserService service;
    private final UserProfileModelAssembler profileAssembler;
    private final UserModelAssembler userAssembler;

    public UserController(UserService service, UserModelAssembler userAssembler, UserProfileService userProfileService, UserProfileModelAssembler profileAssembler) {
        this.service = service;
        this.userAssembler = userAssembler;
        this.userProfileService = userProfileService;
        this.profileAssembler = profileAssembler;
    }

    @PatchMapping("/{username}")
    @PreAuthorize("authentication.name == #username")
    public EntityModel<UserDTO> updateUser(@PathVariable String username, @RequestBody LoginHelper user) {
        return userAssembler.toModel(service.updateUser(username, user));
    }

    //Related to user profile

    @GetMapping("/{username}/profile")
    public EntityModel<UserProfileDTO> getUserProfile(@PathVariable String username) {
        return profileAssembler.toModel(userProfileService.getAndUpdate(username));
    }

    @PutMapping(value = "/{username}/profile", params = "bookId")
    public EntityModel<UserProfileDTO> addFavoriteBook(@PathVariable String username, @RequestParam Long bookId) {
        return profileAssembler.toModel(userProfileService.addFavoriteBook(username, bookId));
    }

    @PatchMapping(value = "/{username}/profile", params = "bookId")
    public EntityModel<UserProfileDTO> removeFavoriteBook(@PathVariable String username, @RequestParam Long bookId) {
        return profileAssembler.toModel(userProfileService.removeFavoriteBook(username, bookId));
    }

    @PutMapping(value = "/{username}/profile", params = "authorId")
    public EntityModel<UserProfileDTO> addFavoriteAuthor(@PathVariable String username, @RequestParam Long authorId) {
        return profileAssembler.toModel(userProfileService.addFavoriteAuthor(username, authorId));
    }

    @PatchMapping(value = "/{username}/profile", params = "authorId")
    public EntityModel<UserProfileDTO> removeFavoriteAuthor(@PathVariable String username, @RequestParam Long authorId) {
        return profileAssembler.toModel(userProfileService.removeFavoriteAuthor(username, authorId));
    }
}
