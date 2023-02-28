package de.telran.calendar.repository;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

        //В репозиториях добавить кастомные методы для поиска сущностей по полям:
        //    User:
        //        - name
        //        - name & lastName
        //        - userName
        //        - email
        //        - event

    List<User> findByName(String name);
    List<User> findByNameAndLastName(String name, String lastName);
    List<User> findByUserName(String username);
    List<User> findByEmail(String email);
    List<User> findByEvent(Event event, PageRequest of);


}
