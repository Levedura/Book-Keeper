package bookers.bookkeeper.bookentry;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userbooklist", schema = "public", catalog = "BookKeeper")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = BookEntry.class)
public class BookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @Column(name = "score")
    private float userscore;

    @Column(name = "pages_read")
    private Integer pagesRead;

    @Column(name = "date_added")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateAdded;

    @Column(name = "date_finished")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateFinished;


    @Enumerated(EnumType.STRING)
    private Status status;

}
