package de.telran.calendar.repository;

import de.telran.calendar.model.Event;

import java.util.Optional;

public interface EventRepository {

    Optional<Event> find(long id);

    long save(Event event);

    boolean delete(Event e);

}
