package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import de.telran.calendar.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserGroupService {
    long createUserGroup(UserGroup userGroup);
    UserGroup getUserGroupById(long id);
    void updateUserGroup(long id, UserGroup userGroup);
    void deleteUserGroup(long id);
    List<UserGroup> getUserGroupsByName(String name);
    List<UserGroup> getUserGroupsByEvent(Event event, int limit, int offset);
    List<UserGroup> getUserGroupsByUser(User user, int limit, int offset);

    Page<UserGroup> getByFilter(String name, Long eventId, Long userId, Pageable pageable);


}
