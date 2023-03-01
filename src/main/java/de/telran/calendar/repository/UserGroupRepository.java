package de.telran.calendar.repository;

import de.telran.calendar.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT DISTINCT g FROM UserGroup g " +
            "LEFT JOIN g.events e " +
            "LEFT JOIN g.users u " +
            "WHERE lower(g.name) LIKE lower(concat('%', :searchTerm, '%')) " +
            "OR e.id = :eventId " +
            "OR u.id = :userId")
    Page<UserGroup> findByNameOrEventsOrUsers(@Param("searchTerm") String searchTerm,
                                              @Param("eventId") Long eventId,
                                              @Param("userId") Long userId,
                                              Pageable pageable);




}
