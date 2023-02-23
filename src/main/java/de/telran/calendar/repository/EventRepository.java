package de.telran.calendar.repository;

import de.telran.calendar.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

//    Optional<Event> find(long id);
//    long save(Event event);
//    boolean delete(Event e);

}
