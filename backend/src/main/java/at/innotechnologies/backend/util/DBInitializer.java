package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.library.*;
import at.innotechnologies.backend.user.*;
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
    @NonNull private LibraryHelper libraryHelper;

    private boolean alreadyInitialized;

    private void addRoomsToLibrary(Library library) {
        Room technology = new RoomMySql();
        technology.setName("Technology");
        technology.setCapacity(10000);
        library.addRoom(technology);
        ((RoomMySql)technology).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 1));

        technology = roomRepository.save(technology);

        Room medicine = new RoomMySql();
        medicine.setName("Medicine");
        medicine.setCapacity(10000);
        library.addRoom(medicine);
        ((RoomMySql)medicine).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 2));

        medicine = roomRepository.save(medicine);

        Room nature = new RoomMySql();
        nature.setName("Nature");
        nature.setCapacity(10000);
        library.addRoom(nature);
        ((RoomMySql)nature).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), 3));

        nature = roomRepository.save(nature);
    }

    @EventListener(ApplicationReadyEvent.class) // gets executed when application starts the first time
    public void initializeDBOnApplicationReady() {
        if (!alreadyInitialized) {
            List<Library> libraries = libraryRepository.findAll();

            if (libraries.isEmpty()) {
                Library library1 = new LibraryMySql();
                library1.setName("Austrian National Library");
                library1.setAddressCity("1010 Vienna");
                library1.setAddressStreet("Herrengasse 9");

                library1 = libraryRepository.save(library1);
                addRoomsToLibrary(library1);
                libraries.add(library1);

                Library library2 = new LibraryMySql();
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
                Customer customer1 = new CustomerMySql();
                customer1.setName("Lukas Hinterleitner");
                customer1.setEmail("lukas.hinterleitner98@gmail.com");
                customer1.setPhoneNumber("+43981723462349");
                customer1.setRegistrationDate(LocalDate.now());

                customer1 = (Customer) userRepository.save(customer1);

                Employee employee1 = new EmployeeMySql();
                employee1.setName("Mitarbeiter 1");
                employee1.setEmail("mitarbeiter1@library.com");
                employee1.setSalary(5000.0);
                employee1.setHiringDate(LocalDate.now());
                employee1.setLibrary(libraries.get(0));

                employee1 = (Employee) userRepository.save(employee1);
            }

            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                Book harryPotter1 = new BookMySql();
                harryPotter1.setName("Data Science - The easy way");
                harryPotter1.setAmountPages(350);

                harryPotter1 = bookRepository.save(harryPotter1);
                books.add(harryPotter1);

                libraryHelper.addBookToRoom(libraries.get(0).getRooms().get(0), harryPotter1, 20);
            }

            alreadyInitialized = true;
        }
    }
}
