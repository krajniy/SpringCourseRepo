package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import de.telran.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public long createUser(User user) {
        return repository.save(user).getId();
    }

    @Override
    public User getUser(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateUser(long id, User user) {
        User u = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setUserName(user.getUserName());
        u.setBirthDate(user.getBirthDate());
        u.setEmail(user.getEmail());
        u.setEvents(user.getEvents());
        u.setUserGroups(user.getUserGroups());
        u.setUpdatedAt(LocalDateTime.now());
        repository.save(u);

    }

    @Override
    public void deleteUser(long id) {
        User u = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(u);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<User> getUsersByNameAndLastName(String name, String lastName) {
        return repository.findByNameAndLastName(name, lastName);
    }

    @Override
    public List<User> getUsersByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByEvent(Event event, int limit, int offset) {
        return repository.findByEvent(event, PageRequest.of(offset, limit));
    }
}
