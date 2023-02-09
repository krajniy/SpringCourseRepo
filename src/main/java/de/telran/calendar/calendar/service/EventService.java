package de.telran.calendar.calendar.service;


import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Object get(long id);
//    Object get(LocalDate date);
//
//    List<Object> get(String name);

    List<Object> get(LocalDate date, String name);

    void update(long id, Object event);

    void create(Object event);

    void delete(long id);

    List<Object> getByFilter(Object filter);

}
