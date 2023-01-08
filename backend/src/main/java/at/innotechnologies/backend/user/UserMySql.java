package at.innotechnologies.backend.user;

import at.innotechnologies.backend.borrow.BorrowsMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserMySql implements User {

    @EqualsAndHashCode.Include
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = BorrowsMySql.class, mappedBy = "user")
    private Set<BorrowsMySql> borrowed = new HashSet<>();
}
