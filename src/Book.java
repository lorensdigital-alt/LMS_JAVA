public class Book extends LibraryItem {
    //fields id
    private String author;
    private String isbn;
    private int publicationYear;
    private String genre;

    //Constructor
    public Book (String title, String author, String isbn, int publicationYear, String genre){
        super(title);

        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String getGenre() {
        return this.genre;
    }

    @Override
    public String toString() {
        return  "Book#: "+getId()+ "|"
               + getTitle() +
                " | Author: " + author +
                " | Year: " + publicationYear +
                " | Genre: " + genre +
                " | Status: " + (isAvailable() ? "Available" : "Borrowed");
    }
}
