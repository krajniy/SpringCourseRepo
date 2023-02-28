package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;

import java.util.List;

public interface UserService {

    long createUser(User user);
    User getUser(long id);
    void updateUser(long id, User user);
    void deleteUser(long id);

    List<User> getUsersByName(String name);
    List<User> getUsersByNameAndLastName(String name, String lastName);
    List<User> getUsersByUserName(String userName);
    List<User> getUsersByEmail(String email);
    List<User> getUsersByEvent(Event event, int limit, int offset);

}
