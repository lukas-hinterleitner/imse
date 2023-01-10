package at.innotechnologies.backend.user;

import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.util.Migration;
import at.innotechnologies.backend.util.exception.InvalidDataException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserRepository {
    @NonNull
    private UserRepositoryMySql userRepositoryMySql;

    @NonNull
    private CustomerRepository customerRepository;

    @NonNull
    private EmployeeRepository employeeRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    public Optional<User> findByEmail(String email) {
        if (Migration.migrationInitialized) {
            Optional<CustomerMongo> customerMongo = customerRepository.findByEmail(email);

            if (customerMongo.isPresent())
                return customerMongo.map(customerMongo1 -> customerMongo1);
            else {
                final List<Library> libraries = libraryRepository.findAll();
                return libraries
                        .stream()
                        .map(Library::getEmployees)
                        .flatMap(List::stream)
                        .filter(employee -> employee.getEmail().equals(email))
                        .findFirst()
                        .map(employee -> employee);
            }
        } else {
            return userRepositoryMySql.findByEmail(email).map(userMySql -> userMySql);
        }
    }

    public Optional<User> findById(String id) {
        if (Migration.migrationInitialized) {
            Optional<CustomerMongo> customerMongo = customerRepository.findById(id);

            if (customerMongo.isPresent())
                return customerMongo.map(customerMongo1 -> customerMongo1);
            else {
                final List<Library> libraries = libraryRepository.findAll();
                return libraries
                        .stream()
                        .map(Library::getEmployees)
                        .flatMap(List::stream)
                        .filter(employee -> employee.getId().equals(id))
                        .findFirst()
                        .map(employee -> employee);
            }
        } else {
            return userRepositoryMySql.findById(id).map(userMySql -> userMySql);
        }
    }

    public User save(User user) {
        if (Migration.migrationInitialized) {
            if (user instanceof CustomerMySql customerMySql) {
                return customerRepository.save(Migration.customerToMongo(customerMySql));
            } else if (user instanceof EmployeeMySql employeeMySql) {
                return Migration.employeeToMongo(employeeMySql);
            } else if (user instanceof CustomerMongo customerMongo) {
                return customerRepository.save(customerMongo);
            } else if (user instanceof EmployeeMongo employeeMongo) {
                return employeeRepository.save(employeeMongo);
            } else {
                throw new InvalidDataException("wrong user type");
            }
        } else {
            return userRepositoryMySql.save((UserMySql) user);
        }
    }

    public List<User> findAll() {
        if (Migration.migrationInitialized) {
            return Stream.concat(
                    customerRepository.findAll().stream().map(customerMongo -> (User) customerMongo),
                    employeeRepository.findAll().stream().map(employeeMongo -> (User) employeeMongo)
            ).collect(Collectors.toList());
        } else {
            return userRepositoryMySql.findAll().stream().map(userMySql -> (User) userMySql).collect(Collectors.toList());
        }
    }

    public List<User> saveAll(List<User> users) {
        return users.stream().map(this::save).collect(Collectors.toList());
    }
}
