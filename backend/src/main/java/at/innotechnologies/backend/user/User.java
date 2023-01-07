package at.innotechnologies.backend.user;

public interface User {
    Integer id = null;
    String name = null;
    String email = null;

    void setName(String name);
    void setEmail(String email);

    String getName();
    String getEmail();
}
