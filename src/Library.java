import java.util.ArrayList;
import java.util.List;

public class Library {
    // Fields
    private final String libraryName;

    private final List<Member> members;
    private final List<Loan> loans;
    private final List<LibraryItem> items;

    // Constructor to initialize the library
    public Library(String libraryName) {
        this.libraryName = libraryName;

        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.items = new ArrayList<>();
    }


    public List<Book> getBooks(){
        List<Book> foundBooks = new ArrayList<>();
        for (LibraryItem item : items){
            if (item instanceof Book book){
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    public List<Magazine> getMagazines() {
        List<Magazine> foundMagazines = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item instanceof Magazine magazine) {
                foundMagazines.add(magazine);
            }
        }

        return foundMagazines;
    }

    public List<LibraryItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    // SEARCH BOOK
    public LibraryItem searchItem(long id) {
        for (LibraryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        throw new IllegalStateException(
                "Library item with ID " + id + " not found"
        );
    }

    // REMOVE BOOK
    public void removeItem(long id) {
        LibraryItem item = searchItem(id);

        if (item.isAvailable()) {
            items.remove(item);
        } else {
            throw new IllegalStateException(
                    "Sorry, you can't remove a borrowed item"
            );
        }
    }
    // BORROW BOOK
    public void borrowItem(long memberId, long itemId) {
        Member foundMember = searchMember(memberId);
        LibraryItem item = searchItem(itemId);

        int activeLoanCount = getActiveLoansForMember(foundMember.getId()).size();
        if (activeLoanCount>= 5){
            throw new IllegalStateException("Unable to borrow. Maximum of 5 items allowed. Please return an existing loan first.");
        }
                item.borrow();
                Loan loan = new Loan(foundMember, item);
                loans.add(loan);
    }

    // RETURN BOOK
public void returnItem(long loanId) {
        Loan foundLoan = searchLoan(loanId);
        foundLoan.returnItem();
    }

    public Loan searchLoan(long loanId){
        for (Loan loan: loans){
            if (loan.getId() == loanId){
                return loan;
            }
        }
        throw new IllegalStateException("Loan with ID " + loanId + " not found");
    }

    // CHECK IF MEMBER EXISTS
    public boolean memberExistsById(long id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // ADD MEMBER
    public void addMember(Member member) {
        if (memberExistsById(member.getId())) {
            throw new IllegalStateException("Member with id " + member.getId() + " already exists");
        }
        members.add(member);
    }

    // SEARCH MEMBER
    public Member searchMember(long id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        throw new IllegalStateException("Member with id " + id + " not found");
    }

    public List<Loan> getActiveLoansForMember(long id){
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan: loans){
            if (loan.getMember().getId() == id && !loan.isReturned()){
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }

    public Loan getActiveLoanForItem(long id) {
        for (Loan loan : loans) {
            if (loan.getItem().getId() == id && !loan.isReturned()) {
                return loan;
            }
        }

        return null;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (LibraryItem item : items){
        if (item instanceof Book book){
            if (book.isAvailable()){
                availableBooks.add(book);
            }
        }
    }
        return availableBooks;
    }

    public List<LibraryItem> getBorrowedItems() {
        List<LibraryItem> borrowedItems = new ArrayList<>();

        for (LibraryItem item : items) {
            if (!item.isAvailable()) {
                borrowedItems.add(item);
            }
        }

        return borrowedItems;
    }

    public List<Loan> getActiveLoans(){
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan : loans){
            if (!loan.isReturned()){
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }

    public List<Loan> getReturnedLoans(){
        List<Loan> returnedLoans = new ArrayList<>();
        for (Loan loan : loans){
            if (loan.isReturned()){
                returnedLoans.add(loan);
            }
        }
        return returnedLoans;
    }

    public List<LibraryItem> searchItemsByTitle(String title) {
        List<LibraryItem> foundItems = new ArrayList<>();
        title = title.toLowerCase();

        for (LibraryItem libraryItem : items) {
            String itemTitle = libraryItem.getTitle().toLowerCase();
            if (itemTitle.contains(title)) {
                foundItems.add(libraryItem);
            }
        }
        return foundItems;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        author = author.toLowerCase();

        for (LibraryItem item : items) {
            if (item instanceof Book book) {
            String bookAuthor = book.getAuthor().toLowerCase();
            if (bookAuthor.contains(author)) {
                foundBooks.add(book);
            }
        }
    }
        return foundBooks;
    }

    public List<Book> searchBooksByGenre(String genre){
        List<Book> foundBooks = new ArrayList<>();

        genre = genre.toLowerCase();
        for (LibraryItem item : items) {
            if (item instanceof Book book) {

                String bookGenre = book.getGenre().toLowerCase();
                if (bookGenre.contains(genre)) {
                    foundBooks.add(book);
                }
            }
        }
        return foundBooks;
    }

    public List<Book> searchBooksByIsbn(String isbn){
        List<Book> foundBooks = new ArrayList<>();

        isbn = isbn.toLowerCase();
        for (LibraryItem item : items) {
            if (item instanceof Book book) {
                String bookIsbn = book.getIsbn().toLowerCase();
                if (bookIsbn.contains(isbn)) {
                    foundBooks.add(book);
                }
            }
        }

        return foundBooks;
    }

    public List<Member> searchMembersByName(String name){
        List<Member> foundMembers = new ArrayList<>();

        name = name.toLowerCase();
        for (Member member : members){
            String memberName = member.getName().toLowerCase();
            if (memberName.contains(name)){
                foundMembers.add(member);
            }
        }

        return foundMembers;
    }

    public List<Member> searchByEmail(String email){
        List<Member> foundMembers = new ArrayList<>();
        email=email.toLowerCase();

        for (Member member : members){
            String memberEmal =member.getEmail().toLowerCase();
            if (memberEmal.contains(email)){
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    public List<LibraryItem> getAvailableItems() {
        List<LibraryItem> availableItems = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item.isAvailable()) {
                availableItems.add(item);
            }
        }

        return availableItems;
    }
}