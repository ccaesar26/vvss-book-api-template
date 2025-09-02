package ro.unitbv.fmi.vvss.bookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unitbv.fmi.vvss.bookapi.model.Book;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA va implementa automat aceasta metoda
    Optional<Book> findByIsbn(String isbn);
}