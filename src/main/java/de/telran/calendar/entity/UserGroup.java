package de.telran.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_groups")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserGroup {
    //    - id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //        - name
    @NotEmpty
    @NotBlank
    @NonNull
    @Column(name = "group_name", nullable = false, length = 280)
    private String name;


    //        - users (Set<User>)
    @ManyToMany
    private Set<User> users = new HashSet<>();

    //        - events (Set<Event>)
    @ManyToMany
    private Set<Event> events = new HashSet<>();

    //        - createdAt (date)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //        - updatedAt (date)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
