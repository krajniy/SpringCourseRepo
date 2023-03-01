package de.telran.calendar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

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

    //    @NotEmpty
//    @NotBlank
    @NonNull
    @Column(name = "event_date")
    private LocalDate date;

    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "event_name", nullable = false, length = 280)
    private String name;


    //    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGroup_id")
    private UserGroup userGroup;

//    @ManyToMany(mappedBy = "events")
//    private Set<User> users;

}
