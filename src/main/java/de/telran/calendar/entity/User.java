package de.telran.calendar.entity;

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

    @Column(name = "birth_date")
    private LocalDate birthDate;

    //TODO
//        - email
    private String email;

    //TODO
    //        - events (Set<Event>)
    @ManyToMany(mappedBy = "users")
    private Set<Event> events = new HashSet<>();

    //TODO
    //        - userGroups (Set<UserGroup>)
    @ManyToMany(mappedBy = "users")
    private Set<UserGroup> userGroups = new HashSet<>();

    //TODO
    //        - createdAt (date)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //TODO
    //        - updatedAt (date)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
