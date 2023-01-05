package at.innotechnologies.backend.library;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPrimaryKey implements Serializable {
    private Integer libraryId;
    private Integer roomId;
}
