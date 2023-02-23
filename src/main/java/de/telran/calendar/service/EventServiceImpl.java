package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @PostConstruct
    public void fillBD(){
        EventSeeder.seed(repository);
    }


    public long create(Event event) {
        return repository.save(event).getId();
    }

    public Event get(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void update(long id, String eventName) {
        Event e = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        e.setName(eventName);

    }

    @Override
    public void update(long id, LocalDate date) {
        Event e = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        e.setDate(date);
    }

    public void delete(long id) {
        Event e = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(e);


    }

    public List<Event> getByFilter(Object filter) {
        return null;
    }

    public List<Event> get(LocalDate date, String name) {

        return null;
    }

}
