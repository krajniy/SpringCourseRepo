package de.telran.calendar.repository;

import de.telran.calendar.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

        //В репозиториях добавить кастомные методы для поиска сущностей по полям:
//    UserGroup
//        - name
//        - event
//        - user

    List<UserGroup> findByName(String name);
    List<UserGroup> findByEventsId(long eventId);
    List<UserGroup> findByUsersId(long userId);



}
