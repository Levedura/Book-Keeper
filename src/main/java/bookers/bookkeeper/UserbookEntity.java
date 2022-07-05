package bookers.bookkeeper;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "userbook", schema = "public", catalog = "BookRep")
@Data
public class UserbookEntity {

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookid;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userid;

    private BigInteger score;
    private Integer pagesRead;
    private Date dateAdded;
    private Date dateFinished;
    @Enumerated(EnumType.STRING)
    private Status status;

}
