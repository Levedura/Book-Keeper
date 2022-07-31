package bookers.bookkeeper.user.dto;

import lombok.Data;

@Data
public class LoginHelper {
    private String username;
    private String password;
    private String email;
}
