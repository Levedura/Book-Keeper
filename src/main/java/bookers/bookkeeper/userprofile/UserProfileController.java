package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.userprofile.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile/{username}")
public class UserProfileController {

    UserProfileService userProfileService;
    UserProfileModelAssembler modelAssembler;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, UserProfileModelAssembler modelAssembler) {
        this.userProfileService = userProfileService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping()
    public EntityModel<UserProfileDTO> getUserProfile(@PathVariable String username) {
        return modelAssembler.toModel(userProfileService.getAndUpdate(username));
    }

    public CollectionModel<EntityModel<UserProfileDTO>> getAll() {
        return modelAssembler.toCollectionModel(userProfileService.getAllEntities());
    }

    @PutMapping("/book/{bookId}")
    public EntityModel<UserProfileDTO> addFavoriteBook(@PathVariable String username, @PathVariable Long bookId) {
        return modelAssembler.toModel(userProfileService.addFavoriteBook(username, bookId));
    }

    @PatchMapping("/book/{bookId}")
    public EntityModel<UserProfileDTO> removeFavoriteBook(@PathVariable String username, @PathVariable Long bookId) {
        return modelAssembler.toModel(userProfileService.removeFavoriteBook(username, bookId));
    }

    @PutMapping("/author/{authorId}")
    public EntityModel<UserProfileDTO> addFavoriteAuthor(@PathVariable String username, @PathVariable Long authorId) {
        return modelAssembler.toModel(userProfileService.addFavoriteAuthor(username, authorId));
    }

    @PatchMapping("/author/{authorId}")
    public EntityModel<UserProfileDTO> removeFavoriteAuthor(@PathVariable String username, @PathVariable Long authorId) {
        return modelAssembler.toModel(userProfileService.removeFavoriteAuthor(username, authorId));
    }
}
