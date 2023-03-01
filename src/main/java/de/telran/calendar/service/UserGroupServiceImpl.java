package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import de.telran.calendar.entity.UserGroup;
import de.telran.calendar.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService{
    @Autowired
    private UserGroupRepository repository;


    @Override
    public long createUserGroup(UserGroup userGroup) {
        return 0;
    }

    @Override
    public UserGroup getUserGroupById(long id) {
        return null;
    }

    @Override
    public void updateUserGroup(long id, UserGroup userGroup) {

    }

    @Override
    public void deleteUserGroup(long id) {

    }

    @Override
    public List<UserGroup> getUserGroupsByName(String name) {
        return null;
    }

    @Override
    public List<UserGroup> getUserGroupsByEvent(Event event, int limit, int offset) {
        return null;
    }

    @Override
    public List<UserGroup> getUserGroupsByUser(User user, int limit, int offset) {
        return null;
    }

    @Override
    public Page<UserGroup> getByFilter(String name, Long eventId, Long userId, Pageable pageable) {
        return repository.findByNameOrEventsOrUsers(name,eventId,userId,pageable);
    }
}
