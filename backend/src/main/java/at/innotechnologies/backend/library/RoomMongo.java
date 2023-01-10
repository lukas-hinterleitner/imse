package at.innotechnologies.backend.library;

import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class RoomMongo implements Room {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @EqualsAndHashCode.Include
    @Field
    private String name;

    @Field
    private Integer capacity;

    @EqualsAndHashCode.Exclude
    private Set<ContainsMongo> contains = new HashSet<>();

    @Override
    public Set<Contains> getContains() {
        return this.contains.stream().map(containsMongo -> (Contains) containsMongo).collect(Collectors.toSet());
    }


}
