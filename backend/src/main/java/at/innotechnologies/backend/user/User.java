package at.innotechnologies.backend.user;

public interface User {
    void setName(String name);
    void setEmail(String email);

    String getName();
    String getEmail();
    String getId();
}
