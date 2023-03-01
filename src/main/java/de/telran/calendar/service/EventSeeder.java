package de.telran.calendar.service;

import de.telran.calendar.entity.Event;
import de.telran.calendar.entity.User;
import de.telran.calendar.repository.EventRepository;
import de.telran.calendar.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

public class EventSeeder {
    public static void seed(EventRepository eventRepository, UserRepository userRepository){
        for (int i = 0; i < 10; i++) {
            User user = new User("John " + (i+1), "Doe " + (i+1), "johndoe " + (i+1),
                     "" + (i+1) +"johndoe@example.com");
            Event event = new Event(LocalDate.now(), "Test event " + (i+1));
            user.addEvent(event);
            event.setUser(user);
            userRepository.save(user);
            eventRepository.save(event);
        }
    }
}
