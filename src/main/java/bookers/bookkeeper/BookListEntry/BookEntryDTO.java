package bookers.bookkeeper.BookListEntry;

import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.user.User;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class BookEntryDTO {


    private Long bookid;

    private Long userid;

    private float userscore;

    private Integer pagesRead;

    private Date dateAdded;

    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

}


