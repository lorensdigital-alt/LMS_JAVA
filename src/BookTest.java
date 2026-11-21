import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void newBookShouldBeAvailable() {
        Book book = new Book("RichDad PoorDad", "Robert", "978-0456543", 2026, "Economics");
        assertTrue(book.isAvailable());
    }

    @Test
    void borrowedBookShouldNotBeAvailable() {
        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        book.borrow();

        assertFalse(book.isAvailable());
    }

    @Test
    void borrowingAlreadyBorrowedBookShouldGiveCorrectMessage() {
        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        book.borrow();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> book.borrow()
        );

        assertEquals("Item is already borrowed", exception.getMessage());
    }
}
