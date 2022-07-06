package bookers.bookkeeper;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "score", schema = "public", catalog = "BookRep")
@Data
public class Score {
    @Id
    private int id;
    @Column(name = "score")
    private float score;

}