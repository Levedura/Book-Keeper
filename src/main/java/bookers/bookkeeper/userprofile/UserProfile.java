package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    User user;

    Float meanScore;

    Integer pagesRead;

    Integer booksRead;

    @OneToMany
    List<Book> favorites;
}