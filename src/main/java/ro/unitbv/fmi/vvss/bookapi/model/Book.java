package ro.unitbv.fmi.vvss.bookapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok: genereaza automat getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: genereaza constructor fara argumente
@AllArgsConstructor // Lombok: genereaza constructor cu toate argumentele
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;
}