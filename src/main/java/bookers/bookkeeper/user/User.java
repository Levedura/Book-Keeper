package bookers.bookkeeper.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "public", catalog = "BookRep")
@Data
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userid")
    private List<UserBook> userbooks;

}
