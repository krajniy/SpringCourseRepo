package de.telran.calendar.calendar.controller;

import de.telran.calendar.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
    //TODO date, name, description, location, startTime, endTime
    @Autowired
    private EventService eventService;

    @GetMapping("/event/{id}")
    public Object getEvent(@PathVariable long id) {
        // Получаем event по id: отправляем GET request на "/event/{id}"
        // с id в path variable,
        // и получаем объект event, если он существует
        return eventService.get(id);
    }
//
//    @GetMapping("/event")
//    public Object getEvent(@RequestParam LocalDate date) {
//        return eventService.get(date);
//    }
//
//    @GetMapping("/event")
//    public List<Object> getEvent(@RequestParam String name) {
//        return eventService.get(name);
//    }


    @GetMapping("/events")
    public List<Object> getEvents(@RequestParam(required = false) LocalDate date,
                                  @RequestParam(required = false) String name) {
        // Получить список events по дате или имени:
        // Отправляем GET запрос на "/events" либо с датой, либо с именем в качестве requestParam,
        // получаем list ивентов, соответствующих запросу

        return eventService.get(date, name);
    }

    @PutMapping("/event/{id}")
    public void updateEvent(@PathVariable long id, @RequestBody Object event) {
        // Обновляем ивент: отправляем PUT request на "/event/{id}"
        // где id передается в path variable а обновляемое событие передается как объект
        eventService.update(id, event);
    }

    @PostMapping("/event")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Object event) {
        // Создаем новый ивент: отправляем POST request на "/event"
        // вместе с объектом типа event в теле запроса и получаем
        // ответ 201 Created если ивент успешно создан
        eventService.create(event);

    }

    @DeleteMapping("/event/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable long id) {
        // Удаляем ивент: отправляем DELETE request на "/event/{id}"
        // передаем id в path variable, получаем 204 No Content response, если ивент успешно удален
        eventService.delete(id);

    }

    @PostMapping("/events/filter")
    public List<Object> getEventsByFilter(@RequestBody Object filter) {

        // Получаем лист ивентов по фильтру: отправляем POST request на "/events/filter"
        // фильтр ивентов передается как object в теле запроса,
        // получаем list ивентов которые соответствуют фильтру в запросе

        return eventService.getByFilter(filter);
    }


}
