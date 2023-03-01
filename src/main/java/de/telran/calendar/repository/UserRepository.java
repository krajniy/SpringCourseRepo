package de.telran.calendar.repository;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
//    List<User> findByEvent(Event event, PageRequest of);






    Page<User> findByNameContainingAndLastNameContainingOrderByIdDesc(String name, String lastName, Pageable pageable);

    Page<User> findByNameContainingOrderByIdDesc(String name, Pageable pageable);

    Page<User> findByLastNameContainingOrderByIdDesc(String lastName, Pageable pageable);

    Page<User> findByUserNameContainingOrderByIdDesc(String userName, Pageable pageable);

    Page<User> findByEmailContainingOrderByIdDesc(String email, Pageable pageable);
    @Query("SELECT DISTINCT u FROM User u INNER JOIN u.events e WHERE e.id = :eventId")
    Page<User> findByEventsIdOrderByIdDesc(@Param("eventId") Long eventId, Pageable pageable);
}
