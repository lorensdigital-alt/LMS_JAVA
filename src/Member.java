public class Member {
    private final long id;
    private String name;
    private String email;
    private static long nextId = 1;

    public Member(String name, String email) {
        id = nextId;
        nextId++;
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member #" + id +
                " | Name: " + name +
                " | Email: " + email;
    }
}