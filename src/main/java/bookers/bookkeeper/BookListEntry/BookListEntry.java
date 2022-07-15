package bookers.bookkeeper.userlist;

import bookers.bookkeeper.Status;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userbooklist", schema = "public", catalog = "BookKeeper")
@Data
public class UserList {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book bookid;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
