package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.userprofile.dto.UserProfileDTO;
import bookers.bookkeeper.userprofile.dto.UserProfileDTOConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    UserProfileDTOConverter converter;
    UserProfileService userProfileService;

    public UserProfileController(UserProfileDTOConverter converter, UserProfileService userProfileService) {
        this.converter = converter;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{username}")
    public UserProfileDTO getUserProfile(@PathVariable String username) {
        return converter.toDto(userProfileService.getAndUpdate(username));
    }

    @PutMapping("/{username}/book/{bookId}")
    public UserProfileDTO addFavoriteBook(@PathVariable String username, @PathVariable Long bookId) {
        return converter.toDto(userProfileService.addFavoriteBook(username, bookId));
    }

    @PatchMapping("/{username}/book/{bookId}")
    public UserProfileDTO removeFavoriteBook(@PathVariable String username, @PathVariable Long bookId) {
        return converter.toDto(userProfileService.removeFavoriteBook(username, bookId));
    }

    @PutMapping("/{username}/author/{authorId}")
    public UserProfileDTO addFavoriteAuthor(@PathVariable String username, @PathVariable Long authorId) {
        return converter.toDto(userProfileService.addFavoriteAuthor(username, authorId));
    }

    @PatchMapping("/{username}/author/{authorId}")
    public UserProfileDTO removeFavoriteAuthor(@PathVariable String username, @PathVariable Long authorId) {
        return converter.toDto(userProfileService.removeFavoriteAuthor(username, authorId));
    }
}
