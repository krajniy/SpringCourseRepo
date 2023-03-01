package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long createUser(User user);
    User getUser(long id);
    void updateUser(long id, User user);
    void deleteUser(long id);

    List<User> getUsersByName(String name);
    List<User> getUsersByNameAndLastName(String name, String lastName);
    List<User> getUsersByUserName(String userName);
    List<User> getUsersByEmail(String email);
//    List<User> getUsersByEvent(Event event, int limit, int offset);
    User getUserById(long id);

    Page<User> getByFilter(String name, String lastName, String userName, String email, Long eventId, Pageable pageable);


}
