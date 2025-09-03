package ro.unitbv.fmi.vvss.bookapi.util;

import lombok.NoArgsConstructor;
import ro.unitbv.fmi.vvss.bookapi.model.Book;

public final class BookFormatter {

    private BookFormatter() {
        // Prevent instantiation
    }

    /**
     * Formats book details into a simple, human-readable summary.
     * Format: "Title by Author (ISBN: 12345)"
     *
     * @param book The book to format. If null, returns an empty string.
     * @return A formatted string summary.
     */
    public static String formatSimple(Book book) {
        if (book == null) {
            return "";
        }
        return String.format("%s by %s (ISBN: %s)", book.getTitle(), book.getAuthor(), book.getIsbn());
    }

    /**
     * Formats book details into a formal citation format (simplified APA style).
     * Format: "Lastname, F. - Title" where F. is the first initial of the first name.
     * If the author has only one name, it's used as is.
     *
     * @param book The book to format. If null, returns an empty string.
     * @return A formatted string citation.
     */
    public static String formatCitation(Book book) {
        if (book == null || book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return "";
        }

        String author = book.getAuthor().trim();
        String[] nameParts = author.split("\\s+");
        String formattedAuthor;

        if (nameParts.length > 1) {
            String lastName = nameParts[nameParts.length - 1];
            String firstNameInitial = nameParts[0].substring(0, 1).toUpperCase();
            formattedAuthor = String.format("%s, %s.", lastName, firstNameInitial);
        } else {
            formattedAuthor = author; // Use the single name as is
        }

        return String.format("%s - %s", formattedAuthor, book.getTitle());
    }
}