public class Main {
    public static void main(String[] args) {
        Library newLibrary = new Library("Zetech");
        //creating three Books
        Book book1 = new Book("RichDad PoorDad", "Robert", "978-0456543", 2026, "Economics");
        Book book2 = new Book("1984", "George Orwell", "978-0451524935", 1949, "Dystopian");
        Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", "978-0261102217", 1937, "Fantasy");
        Book book4 = new Book("The Hobbit", "George Orwell", "978-0261102217", 1937, "Fantasy");
        //Creating member
        Member member1 = new Member("Lauren", "lauren@email.com");

        Member member2 = new Member("Bob Jones", "bob@email.com");

        Member member3 = new Member("Alice Smith", "alice@email.com");

        Magazine magazine1 = new Magazine("Tech Monthly", 25);
        Magazine magazine2 = new Magazine("Science Weekly", 42);

        newLibrary.addMember(member1);
        newLibrary.addMember(member2);
        newLibrary.addMember(member3);

        newLibrary.addItem(book1);
        newLibrary.addItem(book2);
        newLibrary.addItem(book3);
        newLibrary.addItem(book4);
        newLibrary.addItem(magazine1);
        newLibrary.addItem(magazine2);

        System.out.println("INTERFACE TEST");

        Borrowable borrowableBook = book1;

        System.out.println("Before borrow: " + book1.isAvailable());

        borrowableBook.borrow();

        System.out.println("After borrow: " + book1.isAvailable());

        Borrowable borrowableMagazine = magazine1;

        System.out.println("Magazine before borrow: " + magazine1.isAvailable());

        borrowableMagazine.borrow();

        System.out.println("Magazine after borrow: " + magazine1.isAvailable());

        for (LibraryItem item : newLibrary.getItems()) {
            System.out.println(item.getTitle());
            System.out.println(item.isAvailable());
        }

        System.out.println("Interface reference and polymorphism");
        displayItem(book1);
        displayItem(magazine1);

    }
    public static void displayItem(LibraryItem item) {
        System.out.println(item);
    }

}
