package bookers.bookkeeper.bookentry;

import bookers.bookkeeper.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class BookEntryDTO {

    private Long entryid;

    private Long bookid;

    private Long userid;

    private float userscore;

    private Integer pagesRead;

    private Date dateAdded;

    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

}


