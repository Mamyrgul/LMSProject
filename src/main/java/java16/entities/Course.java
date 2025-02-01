package java16.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Course {
    @Id
    @GeneratedValue(generator = "like_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_gen", sequenceName = "like_gen",allocationSize = 1)
    private Long id;
    String title;
    LocalDate dateOfStart;
    String description;
    @ManyToMany
    private List<Instructor> instructors;
    @ManyToMany
    private List<Student> students;
    @OneToMany
    private List<Lesson> lessons;

    public Course(String title, LocalDate dateOfStart, String description) {
        this.title = title;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}
