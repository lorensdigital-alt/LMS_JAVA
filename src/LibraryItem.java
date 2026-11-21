public abstract class LibraryItem implements Borrowable{
    private final long id;
    private static long nextId = 1;
    private final String title;
    private boolean available = true;

    public LibraryItem(String title) {
        this.id = nextId;
        nextId++;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        if (isAvailable()) {
            available = false;
        } else {
            throw new IllegalStateException("Item is already borrowed");
        }
    }

    public void returnItem() {
        if (isAvailable()) {
            throw new IllegalStateException("Item is not currently borrowed");
        }
        available = true;
    }
}