package at.innotechnologies.backend.user;

import at.innotechnologies.backend.util.Migration;
import at.innotechnologies.backend.util.exception.InvalidDataException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public User save(User user) {
        if (Migration.migrationFinished) {
            if (user instanceof CustomerMongo customerMongo) {
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
        if (Migration.migrationFinished) {
            return Stream.concat(
                    customerRepository.findAll().stream().map(customerMongo -> (User) customerMongo),
                    employeeRepository.findAll().stream().map(employeeMongo -> (User) employeeMongo)
            ).collect(Collectors.toList());
        } else {
            return userRepositoryMySql.findAll().stream().map(userMySql -> (User) userMySql).collect(Collectors.toList());
        }
    }
}
