package de.telran.calendar.service;

import de.telran.calendar.entity.User;
import de.telran.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

//    @Override
//    public List<User> getUsersByEvent(Event event, int limit, int offset) {
//        return repository.findByEvent(event, PageRequest.of(offset, limit));
//    }

    @Override
    public User getUserById(long id) {
        return repository.findById(id).get();
    }

    public Page<User> getByFilter(String name, String lastName, String userName, String email, Long eventId, Pageable pageable) {
        if (name != null && lastName != null) {
            return repository.findByNameContainingAndLastNameContainingOrderByIdDesc(name, lastName, pageable);
        } else if (name != null) {
            return repository.findByNameContainingOrderByIdDesc(name, pageable);
        } else if (lastName != null) {
            return repository.findByLastNameContainingOrderByIdDesc(lastName, pageable);
        } else if (userName != null) {
            return repository.findByUserNameContainingOrderByIdDesc(userName, pageable);
        } else if (email != null) {
            return repository.findByEmailContainingOrderByIdDesc(email, pageable);
        } else if (eventId != null) {
            return repository.findByEventsIdOrderByIdDesc(eventId, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }


}
