package de.telran.calendar.service;

import de.telran.calendar.model.Event;
import de.telran.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;


    public long create(Event event) {
        return repository.save(event);
    }

    public Event get(long id) {
        return repository.find(id).orElseThrow(IllegalArgumentException::new);
    }

    public void update(long id, String eventName) {
        Event e = repository.find(id).orElseThrow(IllegalArgumentException::new);
        e.setName(eventName);

    }

    @Override
    public void update(long id, LocalDate date) {
        Event e = repository.find(id).orElseThrow(IllegalArgumentException::new);
        e.setDate(date);
    }

    public void delete(long id) {
        Event e = repository.find(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(e);


    }

    public List<Event> getByFilter(Object filter) {
        return null;
    }

    public List<Event> get(LocalDate date, String name) {

        return null;
    }

}
