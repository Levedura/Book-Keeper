package bookers.bookkeeper.userlist;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class UserListService extends BaseService<UserList,UserListRepository> {

    @Autowired
    public UserListService(UserListRepository userListRepository) {
        super(userListRepository);
    }

    @GetMapping("/userlist/{id}")
    public UserList getUserListById(Long userId){
        return findById(userId);
    }

}
