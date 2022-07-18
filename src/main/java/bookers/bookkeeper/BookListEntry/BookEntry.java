package bookers.bookkeeper.BookListEntry;

import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userbooklist", schema = "public", catalog = "BookKeeper")
@Data
public class BookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    @JsonIdentityReference(alwaysAsId =true )
    private Book bookid;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId =true)
    private User userid;

    @Column(name = "score")
    private float userscore;

    @Column(name = "pages_read")
    private Integer pagesRead;

    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "date_finished")
    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

}
