package de.telran.calendar.repository;

import de.telran.calendar.model.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class EventRepositoryImpl /* implements EventRepository*/{

    private static final List<Event> list = new ArrayList<>();

    @PostConstruct
    public void populateList(){
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(new Event(i +1, LocalDate.now(), "Event " + i+1));
        }
    }

//    @Override
    public Optional<Event> find(long id) {
        Event event = null;
        try {
            event = list.get((int) id);
        } catch (Exception ignored){
        }

        return Optional.ofNullable(event);
    }

//    @Override
    public long save(Event event) {
        long id = event.getId();
        if (id == 0) {
            list.add(event);
            return list.size();
        }
        list.set((int) id, event);
        return id;
    }

//    @Override
    public boolean delete(Event e) {
        return list.remove(e);
    }
}
