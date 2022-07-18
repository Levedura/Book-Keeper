package bookers.bookkeeper.user;

import bookers.bookkeeper.BookListEntry.BookEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", schema = "public", catalog = "BookKeeper")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column
    private String name;
    @Column(name = "password")
    private String password;

    @ElementCollection
    private Collection<GrantedAuthority> authorities;

    @OneToMany(mappedBy = "userid")
    private List<BookEntry> userbooks;


    @JsonIgnore
    private Boolean enabled;
    @JsonIgnore
    private Boolean accountNonExpired;
    @JsonIgnore
    private Boolean accountNonLocked;
    @JsonIgnore
    private Boolean isCredentialsNonExpired;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
