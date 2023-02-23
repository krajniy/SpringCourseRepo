package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.repository.EventRepository;

import java.time.LocalDate;

public class EventSeeder {
    public static void seed(EventRepository repository){
        for (int i = 0; i < 10; i++) {
            repository.save(new Event(i +1, LocalDate.now(), "Event " + (i+1)));
        }
    }
}
