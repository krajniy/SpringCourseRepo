package de.telran.calendar.model;

import java.time.LocalDate;

public class Event {
    private long id;
    private LocalDate date;
    private String name;

    public Event() {
    }

    public Event(long id, LocalDate date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }

    public Event(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
