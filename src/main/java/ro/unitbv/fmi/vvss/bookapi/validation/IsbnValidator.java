package ro.unitbv.fmi.vvss.bookapi.validation;

public final class IsbnValidator {

    private IsbnValidator() {
    }

    /**
     * Checks if a string is a valid Book ISBN-13.
     * A valid ISBN-13 must meet the following criteria:
     * 1. It must have exactly 13 digits. No other characters are allowed.
     * 2. It must start with the "978" or "979" prefix (informally known as "Bookland").
     *    Prefixes like "977" (ISSN) are considered invalid for books.
     * 3. The last digit is a check digit calculated using a weighted sum of the first 12 digits.
     *
     * @param isbn The string to validate. Can be null.
     * @return true if the string is a valid ISBN-13, false otherwise.
     */
    public static boolean isValid(String isbn) {
        // Rule 1: Check for null and length
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        // Rule 2: Check for "Bookland" prefix
        if (!isbn.startsWith("978") && !isbn.startsWith("979")) {
            return false;
        }

        // Rule 3: Validate digits and calculate check digit
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            char ch = isbn.charAt(i);
            if (!Character.isDigit(ch)) {
                return false; // Contains non-digit characters
            }
            int digit = ch - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;

        char lastChar = isbn.charAt(12);
        if (!Character.isDigit(lastChar)) {
            return false;
        }

        return checkDigit == (lastChar - '0');
    }
}