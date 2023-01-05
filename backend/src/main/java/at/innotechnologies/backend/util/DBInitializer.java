package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.library.*;
import at.innotechnologies.backend.user.Customer;
import at.innotechnologies.backend.user.Employee;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Transactional
public class DBInitializer {
    @NonNull private UserRepository userRepository;
    @NonNull private BookRepository bookRepository;
    @NonNull private RoomRepository roomRepository;
    @NonNull private LibraryRepository libraryRepository;

    private boolean alreadyInitialized;

    private void addRoomsToLibrary(Library library) {
        Room technology = new Room();
        technology.setName("Technology");
        technology.setCapacity(10000);
        technology.setLibrary(library);
        technology.setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 1));

        technology = roomRepository.save(technology);

        Room medicine = new Room();
        medicine.setName("Medicine");
        medicine.setCapacity(10000);
        medicine.setLibrary(library);
        medicine.setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 2));

        medicine = roomRepository.save(medicine);

        Room nature = new Room();
        nature.setName("Nature");
        nature.setCapacity(10000);
        nature.setLibrary(library);
        nature.setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 3));

        nature = roomRepository.save(nature);

    }

    @EventListener(ApplicationReadyEvent.class) // gets executed when application starts the first time
    public void initializeDBOnApplicationReady() {
        if (!alreadyInitialized) {
            List<Library> libraries = libraryRepository.findAll();

            if (libraries.isEmpty()) {
                Library library1 = new Library();
                library1.setName("Austrian National Library");
                library1.setAddressCity("1010 Vienna");
                library1.setAddressStreet("Herrengasse 9");

                library1 = libraryRepository.save(library1);
                addRoomsToLibrary(library1);
                libraries.add(library1);

                Library library2 = new Library();
                library2.setName("Vienna City Library");
                library2.setAddressCity("1010 Vienna");
                library2.setAddressStreet("Felderstraße 1");

                library2 = libraryRepository.save(library2);
                addRoomsToLibrary(library2);
                libraries.add(library2);

                libraries = libraryRepository.saveAll(libraries);
            }

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                Customer customer1 = new Customer();
                customer1.setName("Lukas Hinterleitner");
                customer1.setEmail("lukas.hinterleitner98@gmail.com");
                customer1.setPhoneNumber("+43981723462349");
                customer1.setRegistrationDate(LocalDate.now());

                customer1 = userRepository.save(customer1);

                Employee employee1 = new Employee();
                employee1.setName("Mitarbeiter 1");
                employee1.setEmail("mitarbeiter1@library.com");
                employee1.setSalary(5000.0);
                employee1.setHiringDate(LocalDate.now());
                employee1.setLibrary(libraries.get(0));

                employee1 = userRepository.save(employee1);
            }

            alreadyInitialized = true;
        }
    }
}
