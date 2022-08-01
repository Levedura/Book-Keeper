package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.generics.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userprofile")
public class UserProfileController {
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    UserProfileService userProfileService;

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username){

        return userProfileService.getUserProfile(username);

    }
}
