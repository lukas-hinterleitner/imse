package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.library.*;
import at.innotechnologies.backend.user.*;
import com.github.javafaker.Faker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
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
        technology.setCapacity(20000);
        library.addRoom(technology);
        ((RoomMySql)technology).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), UUID.randomUUID().toString()));

        technology = roomRepository.save(technology);

        Room medicine = new RoomMySql();
        medicine.setName("Medicine");
        medicine.setCapacity(20000);
        library.addRoom(medicine);
        ((RoomMySql)medicine).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), UUID.randomUUID().toString()));

        medicine = roomRepository.save(medicine);

        Room nature = new RoomMySql();
        nature.setName("Nature");
        nature.setCapacity(20000);
        library.addRoom(nature);
        ((RoomMySql)nature).setRoomPrimaryKey(new RoomPrimaryKey(library.getId(), UUID.randomUUID().toString()));

        nature = roomRepository.save(nature);
    }

    public void importData() {
        final Faker faker = new Faker();

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

            final int numLibraries = libraries.size();

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                Customer customer1 = new CustomerMySql();
                customer1.setName("Lukas Hinterleitner");
                customer1.setEmail("testmail@mail.com");
                customer1.setPhoneNumber("+43981723462349");
                customer1.setRegistrationDate(LocalDate.now());

                customer1 = (Customer) userRepository.save(customer1);

                Employee employee1 = new EmployeeMySql();
                employee1.setName("Mitarbeiter 1");
                employee1.setEmail("mitarbeiter1@library.com");
                employee1.setSalary(5000.0);
                employee1.setHiringDate(LocalDate.now());
                ((EmployeeMySql)employee1).setLibrary((LibraryMySql) libraries.get(0));

                employee1 = (Employee) userRepository.save(employee1);

                Employee employee2 = new EmployeeMySql();
                employee2.setName("Mitarbeiter 2");
                employee2.setEmail("mitarbeiter2@library.com");
                employee2.setSalary(5000.0);
                employee2.setHiringDate(LocalDate.now());
                ((EmployeeMySql)employee2).setLibrary((LibraryMySql) libraries.get(1));

                employee2 = (Employee) userRepository.save(employee2);

                for (int i = 0; i < 20; i++) {
                    Employee employee = new EmployeeMySql();

                    employee.setName(faker.name().fullName());
                    employee.setEmail(faker.internet().safeEmailAddress());
                    employee.setSalary((double) faker.number().numberBetween(2000, 5000));
                    employee.setHiringDate(LocalDate.of(2022, faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)));

                    final Library library = libraries.get(faker.number().numberBetween(0, numLibraries));
                    ((EmployeeMySql)employee).setLibrary((LibraryMySql) library);

                    if (library.getId().equals(libraries.get(0).getId())) {
                        ((EmployeeMySql)employee).setSupervisor((EmployeeMySql) employee1);
                    } else {
                        ((EmployeeMySql)employee).setSupervisor((EmployeeMySql) employee2);
                    }

                    employee = (Employee) userRepository.save(employee);
                }
            }

            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                for (int i = 0; i < 60; i++) {
                    final String bookName = faker.book().title();

                    if (books.stream().anyMatch(book -> book.getName().equals(bookName))) {
                        continue;
                    }

                    Book book = new BookMySql();
                    book.setName(bookName);
                    book.setAmountPages(faker.number().numberBetween(150, 500));

                    book = bookRepository.save(book);
                    books.add(book);

                    final Library randomLibrary = libraries.get(faker.number().numberBetween(0, numLibraries));
                    final int numRooms = randomLibrary.getRooms().size();

                    libraryHelper.addBookToRoom(randomLibrary.getRooms().get(faker.number().numberBetween(0, numRooms)), book, faker.number().numberBetween(5, 50));
                }
            }

            alreadyInitialized = true;
        }
    }
}
