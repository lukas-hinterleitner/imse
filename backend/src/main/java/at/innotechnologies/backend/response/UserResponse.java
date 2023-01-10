package at.innotechnologies.backend.response;

import at.innotechnologies.backend.user.Employee;
import at.innotechnologies.backend.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private Boolean isEmployee;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.isEmployee = user instanceof Employee;
    }
}
