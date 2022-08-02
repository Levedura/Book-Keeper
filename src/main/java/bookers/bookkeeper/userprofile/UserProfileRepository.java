package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends GenericRepository<UserProfile> {

    UserProfile getByUser_Username(String username);
}
