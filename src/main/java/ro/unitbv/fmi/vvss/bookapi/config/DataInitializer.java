package ro.unitbv.fmi.vvss.bookapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.unitbv.fmi.vvss.bookapi.model.Book;
import ro.unitbv.fmi.vvss.bookapi.service.BookService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2 // Lombok: adauga automat un logger Log4j2
public class DataInitializer implements CommandLineRunner {

    private final BookService bookService;

    @Override
    public void run(String... args) {
        // Verificam daca baza de date este goala pentru a evita inserarea duplicatelor la fiecare restart
        if (bookService.searchBooks(null, null).isEmpty()) {
            log.info("Database is empty. Initializing with default book data...");

            List<Book> initialBooks = List.of(
                    new Book(null, "La Medeleni.Volumul I", "Ionel Teodoreanu", "9786303428840"),
                    new Book(null, "Enigma Otiliei", "George Călinescu", "9786063350658"),
                    new Book(null, "Mara", "Ioan Slavici", "9786060910596"),
                    new Book(null, "Căpitan la cincisprezece ani", "Jules Verne", "9786303198729"),
                    new Book(null, "Amintiri din copilarie", "Ion Creangă", "9786063318443"),
                    new Book(null, "Risipitorii", "Marin Preda", "9789737883728")
            );

            initialBooks.forEach(bookService::addBook);

            log.info("Finished initializing data. {} books were added.", initialBooks.size());
        } else {
            log.info("Database already contains data. Skipping initialization.");
        }
    }
}