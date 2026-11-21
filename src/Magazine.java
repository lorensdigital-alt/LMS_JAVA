public class Magazine extends LibraryItem {
    private final int issueNumber;

    public Magazine(String title, int issueNumber){
        super(title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String toString() {
        return "Magazine #" + issueNumber +
                " | " + getTitle() +
                " | Status: " + (isAvailable() ? "Available" : "Borrowed");
    }
}