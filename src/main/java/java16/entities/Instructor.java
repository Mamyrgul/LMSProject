package java16.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "instructors")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Instructor {
    @Id
    @GeneratedValue(generator = "like_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_gen", sequenceName = "like_gen",allocationSize = 1)
    private Long id;
    String firstName;
    String lastName;
    String email;
    String phone;
    @ManyToMany
    private List<Course> courses;


    public Instructor(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
