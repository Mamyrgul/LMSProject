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
@Table(name = "students")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Student {
    @Id
    @GeneratedValue(generator = "like_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_gen", sequenceName = "like_gen",allocationSize = 1)
    private Long id;
    private String firstName;
    private String email;
    private String phoneNumber;
    @ManyToMany
    private List<Course> courses;

    public Student(String firstName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
