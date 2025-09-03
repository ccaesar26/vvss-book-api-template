package ro.unitbv.fmi.vvss.bookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.unitbv.fmi.vvss.bookapi.model.Book;
import ro.unitbv.fmi.vvss.bookapi.repository.BookRepository;
import ro.unitbv.fmi.vvss.bookapi.service.exception.BookNotFoundException;
import ro.unitbv.fmi.vvss.bookapi.service.exception.IsbnAlreadyExistsException;

import java.util.List;

@Service
@RequiredArgsConstructor // Dependency Injection prin constructor - esential pentru testabilitate!
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new IsbnAlreadyExistsException("A book with ISBN " + book.getIsbn() + " already exists.");
        }
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found."));
    }

    public List<Book> searchBooks(String title, String author) {
        boolean hasTitle = StringUtils.hasText(title);
        boolean hasAuthor = StringUtils.hasText(author);

        if (hasTitle && hasAuthor) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        } else if (hasTitle) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (hasAuthor) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else {
            return bookRepository.findAll();
        }
    }
}