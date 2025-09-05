package ro.unitbv.fmi.vvss.bookapi.validation;

import org.springframework.util.StringUtils;
import ro.unitbv.fmi.vvss.bookapi.model.Book;

public final class BookDataValidator {

    private BookDataValidator() {
    }

    /**
     * Validates a Book object. A book is valid if:
     * - It is not null.
     * - Title and Author are not null or empty.
     * - Its ISBN is a valid ISBN-13 for books.
     *
     * @param book The book object to validate.
     * @return true if the book is valid, false otherwise.
     */
    public static boolean isValid(Book book) {
        if (book == null) {
            return false;
        }

        // Check for non-empty text fields
        boolean hasTextFields = StringUtils.hasText(book.getTitle()) &&
                StringUtils.hasText(book.getAuthor());

        // Check for valid ISBN format and prefix
        boolean isIsbnValid = IsbnValidator.isValid(book.getIsbn());

        return hasTextFields && isIsbnValid;
    }
}