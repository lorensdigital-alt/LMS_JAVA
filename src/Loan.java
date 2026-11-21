import java.time.LocalDateTime;

public class Loan {
    private final long id;
    private static long nextId=1;
    private final Member member;
    private final LibraryItem item;
    private final LocalDateTime borrowedAt;
    private LoanStatus status;

    public Loan (Member member, LibraryItem item){
        id=nextId;
        nextId++;
        this.member = member;
        this.item =item;
        this.borrowedAt = LocalDateTime.now();
        this.status = LoanStatus.ACTIVE;
    }

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public boolean isReturned() {

        return status ==LoanStatus.RETURNED;
    }

    //Methds
    public void returnItem() {
        if(isReturned()) {
            throw new IllegalStateException("The loan is already marked as returned");
        }
        this.status = LoanStatus.RETURNED;
        item.returnItem();
    }

    @Override
    public String toString() {
        return "Loan #" + id +
                " | Member: " + member.getName() +
                " | Item: " + item.getTitle() +
                " | Borrowed: " + borrowedAt+
                " | Status: " + status;
    }
}
