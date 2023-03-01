package de.telran.calendar.controller;

import de.telran.calendar.entity.Event;
import de.telran.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
    //TODO date, name, description, location, startTime, endTime
    @Autowired
    private EventService eventService;

    @PostMapping("/event")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventService.create(event), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        try {
            return new ResponseEntity<>(eventService.get(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/event/{id}")
    public ResponseEntity<Void> modifyEvent(@PathVariable long id, @RequestBody String eventName) {
        try {
            eventService.update(id, eventName);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/event/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {

        try {
            eventService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //TODO получение списка событий

//    @PostMapping("/events/filter")
//    public List<Event> getEventsByFilter(@RequestBody Object filter) {
//
//        // Получаем лист ивентов по фильтру: отправляем POST request на "/events/filter"
//        // фильтр ивентов передается как object в теле запроса,
//        // получаем list ивентов которые соответствуют фильтру в запросе
//
//        return eventService.getByFilter(filter);
//    }


    @GetMapping("/events")
    public List<Event> getEvents(@RequestParam(required = false) LocalDate date,
                                 @RequestParam(required = false) String name) {
        // Получить список events по дате или имени:
        // Отправляем GET запрос на "/events" либо с датой, либо с именем в качестве requestParam,
        // получаем list ивентов, соответствующих запросу

        return eventService.get(date, name);
    }


}
