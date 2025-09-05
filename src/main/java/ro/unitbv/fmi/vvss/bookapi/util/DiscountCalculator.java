package ro.unitbv.fmi.vvss.bookapi.util;

import ro.unitbv.fmi.vvss.bookapi.model.Book;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DiscountCalculator {

    private DiscountCalculator() {
        // Private constructor to prevent instantiation
    }

    private static final BigDecimal HIGH_PRICE_THRESHOLD = new BigDecimal("100.00");
    private static final BigDecimal STANDARD_DISCOUNT_RATE = new BigDecimal("0.10"); // 10%
    private static final BigDecimal FEATURED_AUTHOR_DISCOUNT_RATE = new BigDecimal("0.05"); // 5% extra
    private static final String FEATURED_AUTHOR = "Jules Verne";

    /**
     * Calculates the discounted price for a single book based on a set of rules.
     * Rules:
     * 1. A standard 10% discount is applied to all books with a price over 100.
     * 2. An additional 5% discount is applied for books written by a featured author ("Jules Verne").
     * 3. Discounts are cumulative (e.g., a book by Jules Verne over 100 gets 15% off).
     * 4. The price is returned as a double, rounded to 2 decimal places.
     *
     * @param book The book for which to calculate the discount. Cannot be null.
     * @param price The original price of the book.
     * @return The new, discounted price as a double.
     */
    public static double calculateDiscountedPrice(Book book, double price) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        var originalPrice = BigDecimal.valueOf(price);
        var totalDiscountRate = BigDecimal.ZERO;

        // Rule 1: Standard discount for expensive books
        if (originalPrice.compareTo(HIGH_PRICE_THRESHOLD) > 0) {
            totalDiscountRate = totalDiscountRate.add(STANDARD_DISCOUNT_RATE);
        }

        // Rule 2: Extra discount for featured author
        if (FEATURED_AUTHOR.equalsIgnoreCase(book.getAuthor())) {
            totalDiscountRate = totalDiscountRate.add(FEATURED_AUTHOR_DISCOUNT_RATE);
        }

        var discountAmount = originalPrice.multiply(totalDiscountRate);
        var discountedPrice = originalPrice.subtract(discountAmount);

        // Rule 4: Round to 2 decimal places
        return discountedPrice.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}