package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.author.AuthorService;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.booklist.BookListRepository;
import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserProfileService extends BaseService<UserProfile, UserProfileRepository> {

    private final UserService userService;
    private final BookListRepository bookListService;
    private final BookService bookService;
    private final AuthorService authorService;

    public UserProfileService(UserProfileRepository rep, UserService userService, BookListRepository bookListService, BookService bookService, AuthorService authorService) {
        super(rep);
        this.userService = userService;
        this.bookListService = bookListService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public UserProfile getUserProfile(String username) {
        return rep.getByUser_Username(username);
    }

    public UserProfile getAndUpdate(String username) {
        return updateUserProfile(username);
    }

    public UserProfile addFavoriteBook(String username, Long bookId) {
        UserProfile userProfile = rep.getByUser_Username(username);
        userProfile.getFavoriteBooks().add(bookService.getBook(bookId));
        return addEntity(userProfile);
    }

    public UserProfile removeFavoriteBook(String username, Long bookId) {
        UserProfile userProfile = rep.getByUser_Username(username);
        userProfile.getFavoriteBooks().remove(bookService.getBook(bookId));
        return addEntity(userProfile);
    }

    public UserProfile addFavoriteAuthor(String username, Long authorId) {
        UserProfile userProfile = rep.getByUser_Username(username);
        userProfile.getFavoriteAuthors().add(authorService.getEntityById(authorId));
        return addEntity(userProfile);
    }

    public UserProfile removeFavoriteAuthor(String username, Long authorId) {
        UserProfile userProfile = rep.getByUser_Username(username);
        userProfile.getFavoriteAuthors().remove(authorService.getEntityById(authorId));
        return addEntity(userProfile);
    }

    public UserProfile updateUserProfile(String username) {
        UserProfile userProfile = getUserProfile(username);
        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(userService.getUserByNameWithCheck(username));
            userProfile.setFavoriteBooks(new ArrayList<>());
            userProfile.setFavoriteAuthors(new ArrayList<>());
        }
        userProfile.setBooksRead(bookListService.booksRead(username));
        userProfile.setPagesRead(bookListService.sumPagesRead(username));
        userProfile.setMeanScore(bookListService.averageUserScore(username));
        return addEntity(userProfile);
    }

}
