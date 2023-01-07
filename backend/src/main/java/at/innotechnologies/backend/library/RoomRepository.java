package at.innotechnologies.backend.library;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRepository {
    @NonNull
    private RoomRepositoryMySql roomRepositoryMySql;

    @NonNull
    private RoomRepositoryMongo roomRepositoryMongo;


    public Room save(Room room) {
        if (Migration.migrationFinished) {
            return roomRepositoryMongo.save((RoomMongo) room);
        } else {
            return roomRepositoryMySql.save((RoomMySql) room);
        }
    }
}
