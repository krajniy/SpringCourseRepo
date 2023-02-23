package de.telran.calendar.repository;

import de.telran.calendar.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

//    Optional<Event> find(long id);
//    long save(Event event);
//    boolean delete(Event e);

}
