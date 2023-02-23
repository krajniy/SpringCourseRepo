package de.telran.calendar.service;


import de.telran.calendar.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {


    Event get(long id);
//    Object get(LocalDate date);
//
//    List<Object> get(String name);

    List<Event> get(LocalDate date, String name);

    void update(long id, String eventName);

    void update(long id, LocalDate date);

    long create(Event event);

    void delete(long id);

    List<Event> getByFilter(Object filter);

}
