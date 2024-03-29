package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    User user;

    Double meanScore;

    Integer pagesRead;

    Integer booksRead;

    @OneToMany
    List<Book> favoriteBooks = new ArrayList<>();

    @OneToMany
    List<Author> favoriteAuthors = new ArrayList<>();
}
