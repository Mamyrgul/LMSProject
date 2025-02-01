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
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
public class Lesson {
    @Id
    @GeneratedValue(generator = "like_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_gen", sequenceName = "like_gen",allocationSize = 1)
    private Long id;
    String title;
    LocalDate dateOfStart;
    String description;
    @ManyToOne
    Course course;

    public Lesson(String title, LocalDate dateOfStart, String description) {
        this.title = title;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }

}
