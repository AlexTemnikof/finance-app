package ru.temnikov.finance.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.temnikov.finance.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> usernameUserMap;

    private User activeUser;

    public UserServiceImpl() {
        usernameUserMap = new HashMap<>();
    }

    public boolean register(final String username, final String password) {
        if (usernameUserMap.getOrDefault(username, null) != null) {
            System.out.println("the user exists");
            return false;
        }

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final var user = new User(username, hashedPassword);
        usernameUserMap.put(username, user);
        activeUser = user;
        return true;
    }

    public boolean login(final String username, final String password) {
        final User user = usernameUserMap.getOrDefault(username, null);
        if (user == null) {
            System.out.println("the user with this username does not exist");
            return false;
        }

        if (!BCrypt.checkpw(password, user.hashedPassword()))  {
            System.out.println("wrong password");
            return false;
        }

        activeUser = user;
        return true;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public List<User> getUsers() {
        return usernameUserMap.values().stream().toList();
    }

    public void setUsers(final List<User> users) {
        Map<String, User> usernameUserMap = users.stream()
                .collect(Collectors.toMap(User::username, user -> user));    }
}
