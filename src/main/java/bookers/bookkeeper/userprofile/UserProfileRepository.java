package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.generics.GenericRepository;

public interface UserProfileRepository extends GenericRepository<UserProfile> {

    UserProfile getByUser_Username(String username);
}
