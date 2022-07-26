package bookers.bookkeeper.bookentry;

import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.user.dto.UserDTO;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class BookEntryDTO {


    private BookDTO bookid;

    private UserDTO userid;

    private float userscore;

    private Integer pagesRead;

    private Date dateAdded;

    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

}


