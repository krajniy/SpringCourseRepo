package de.telran.calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class User {
    //    - id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //        - name
    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "user_name", nullable = false, length = 280)
    private String name;

    //        - lastName
    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "user_lastName", nullable = false, length = 280)
    private String lastName;

    //        - userName
    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "user_userName", nullable = false, length = 280)
    private String userName;

    //TODO
//        - birthDate

//    @NotEmpty
//    @NotBlank
//    @NonNull
    @Column(name = "user_birth_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate birthDate;

    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "user_email", nullable = false, length = 50)
    private String email;

//    @ManyToMany
//    @JoinTable(
//            name = "user_event",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
//    private Set<Event> events;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Event> events = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<UserGroup> userGroups;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void addEvent(Event event) {
        events.add(event);
    }
}
