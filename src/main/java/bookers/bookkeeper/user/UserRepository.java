package bookers.bookkeeper.user;

import bookers.bookkeeper.generics.GenericRepository;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findUserByUsername(String name);
}
