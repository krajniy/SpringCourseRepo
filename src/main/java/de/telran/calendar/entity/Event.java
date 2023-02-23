package de.telran.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "event_date", nullable = false)
    private LocalDate date;

    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "event_name", nullable = false, length = 280)
    private String name;

}
