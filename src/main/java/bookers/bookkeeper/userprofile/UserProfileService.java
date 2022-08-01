package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;

public class UserProfileService  {

    private final UserProfileRepository userProfileRepository;
    private final UserService userService;

    protected UserProfileService(UserProfileRepository userProfileRepository, UserService userService) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
    }


    public UserProfile getUserProfile(String username) {
        return userProfileRepository.getByUser_Username(username);
    }

}
