import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void libraryShouldAddBook() {
        Library library = new Library("Zetech");

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addItem(book);

        assertEquals(1, library.getBooks().size());
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    void libraryShouldAddMember() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        library.addMember(member);

        assertEquals(1, library.getMembers().size());
        assertTrue(library.getMembers().contains(member));
    }

    @Test
    void addingDuplicateMemberShouldThrowException() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        library.addMember(member);

        assertThrows(
                IllegalStateException.class,
                () -> library.addMember(member)
        );
    }

    @Test
    void borrowingBookShouldCreateActiveLoan() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addMember(member);
        library.addItem(book);

        library.borrowItem(member.getId(), book.getId());

        assertEquals(1, library.getActiveLoans().size());

        Loan loan = library.getActiveLoans().get(0);

        assertEquals(member, loan.getMember());
        assertEquals(book, loan.getItem());
        assertFalse(book.isAvailable());
        assertFalse(loan.isReturned());
    }

    @Test
    void borrowingAlreadyBorrowedBookShouldThrowException() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addMember(member);
        library.addItem(book);

        library.borrowItem(member.getId(), book.getId());

        assertThrows(
                IllegalStateException.class,
                () -> library.borrowItem(member.getId(), book.getId())
        );
    }

    @Test
    void returningLoanShouldMakeBookAvailable() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addMember(member);
        library.addItem(book);

        library.borrowItem(member.getId(), book.getId());

        Loan loan = library.getActiveLoans().get(0);

        assertFalse(book.isAvailable());
        assertFalse(loan.isReturned());

        library.returnItem(loan.getId());

        assertTrue(book.isAvailable());
        assertTrue(loan.isReturned());
        assertEquals(0, library.getActiveLoans().size());
    }

    @Test
    void returningAlreadyReturnedLoanShouldThrowException() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addMember(member);
        library.addItem(book);

        library.borrowItem(member.getId(), book.getId());

        Loan loan = library.getActiveLoans().get(0);

        library.returnItem(loan.getId());

        assertThrows(
                IllegalStateException.class,
                () -> library.returnItem(loan.getId())
        );
    }

    @Test
    void memberShouldNotBorrowMoreThanFiveItems() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        library.addMember(member);

        Book book1 = new Book("Book 1", "Author 1", "ISBN1", 2020, "Genre");
        Book book2 = new Book("Book 2", "Author 2", "ISBN2", 2020, "Genre");
        Book book3 = new Book("Book 3", "Author 3", "ISBN3", 2020, "Genre");
        Book book4 = new Book("Book 4", "Author 4", "ISBN4", 2020, "Genre");
        Book book5 = new Book("Book 5", "Author 5", "ISBN5", 2020, "Genre");
        Book book6 = new Book("Book 6", "Author 6", "ISBN6", 2020, "Genre");

        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(book4);
        library.addItem(book5);
        library.addItem(book6);

        library.borrowItem(member.getId(), book1.getId());
        library.borrowItem(member.getId(), book2.getId());
        library.borrowItem(member.getId(), book3.getId());
        library.borrowItem(member.getId(), book4.getId());
        library.borrowItem(member.getId(), book5.getId());

        assertEquals(5, library.getActiveLoansForMember(member.getId()).size());

        assertThrows(
                IllegalStateException.class,
                () -> library.borrowItem(member.getId(), book6.getId())
        );

        assertEquals(5, library.getActiveLoansForMember(member.getId()).size());
    }

    @Test
    void memberShouldBeAbleToBorrowAfterReturningAnItem() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        library.addMember(member);

        Book book1 = new Book("Book 1", "Author 1", "ISBN1", 2020, "Genre");
        Book book2 = new Book("Book 2", "Author 2", "ISBN2", 2020, "Genre");
        Book book3 = new Book("Book 3", "Author 3", "ISBN3", 2020, "Genre");
        Book book4 = new Book("Book 4", "Author 4", "ISBN4", 2020, "Genre");
        Book book5 = new Book("Book 5", "Author 5", "ISBN5", 2020, "Genre");
        Book book6 = new Book("Book 6", "Author 6", "ISBN6", 2020, "Genre");

        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(book4);
        library.addItem(book5);
        library.addItem(book6);

        library.borrowItem(member.getId(), book1.getId());
        library.borrowItem(member.getId(), book2.getId());
        library.borrowItem(member.getId(), book3.getId());
        library.borrowItem(member.getId(), book4.getId());
        library.borrowItem(member.getId(), book5.getId());

        assertEquals(5, library.getActiveLoansForMember(member.getId()).size());

        Loan loan = library.getActiveLoanForItem(book1.getId());

        library.returnItem(loan.getId());

        assertEquals(4, library.getActiveLoansForMember(member.getId()).size());

        library.borrowItem(member.getId(), book6.getId());

        assertEquals(5, library.getActiveLoansForMember(member.getId()).size());
        assertFalse(book6.isAvailable());
    }


    @Test
    void shouldRemoveAvailableItem() {
        Library library = new Library("Zetech");

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addItem(book);

        assertEquals(1, library.getBooks().size());

        library.removeItem(book.getId());

        assertEquals(0, library.getBooks().size());
        assertFalse(library.getItems().contains(book));
    }


    @Test
    void shouldNotRemoveBorrowedItem() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addMember(member);
        library.addItem(book);

        library.borrowItem(member.getId(), book.getId());

        assertThrows(
                IllegalStateException.class,
                () -> library.removeItem(book.getId())
        );

        assertTrue(library.getItems().contains(book));
    }


    @Test
    void shouldThrowExceptionWhenRemovingNonexistentItem() {
        Library library = new Library("Zetech");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> library.removeItem(999)
        );

        assertEquals(
                "Library item with ID 999 not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldFindItemById() {
        Library library = new Library("Zetech");

        Book book = new Book(
                "1984",
                "George Orwell",
                "978-0451524935",
                1949,
                "Dystopian"
        );

        library.addItem(book);

        LibraryItem found = library.searchItem(book.getId());

        assertEquals(book, found);
    }


    @Test
    void modifyingReturnedMemberListShouldNotModifyLibrary() {
        Library library = new Library("Zetech");

        Member member = new Member(
                "Lauren",
                "lauren@email.com"
        );

        library.addMember(member);

        List<Member> members = library.getMembers();

        members.clear();

        assertEquals(1, library.getMembers().size());
        assertTrue(library.getMembers().contains(member));
    }


    @Test
    void testCannotBorrowAlreadyBorrowedItem() {
        // Arrange
        Book book = new Book("Test", "Author", "123", 2024, "Fiction");
        book.borrow(); // First borrow succeeds

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            book.borrow(); // Second borrow should fail
        });
    }

}