package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.borrow.BorrowsMongo;
import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMongo;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryMongo;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMongo;
import at.innotechnologies.backend.user.*;
import at.innotechnologies.backend.util.exception.InvalidDataException;

import java.util.stream.Collectors;

public class Migration {
    public static boolean migrationInitialized = false;

    public static LibraryMongo libraryToMongo(Library library) {
        if (library instanceof LibraryMongo libraryMongo) {
            return libraryMongo;
        } else {
            LibraryMongo libraryMongo = new LibraryMongo();

            libraryMongo.setId(library.getId());
            libraryMongo.setName(library.getName());
            libraryMongo.setAddressCity(library.getAddressCity());
            libraryMongo.setAddressStreet(library.getAddressStreet());
            libraryMongo.setRooms(library.getRooms().stream().map(Migration::roomToMongo).toList());
            libraryMongo.setEmployees(library.getEmployees().stream().map(Migration::employeeToMongo).collect(Collectors.toList()));

            return libraryMongo;
        }
    }

    public static RoomMongo roomToMongo(Room room) {
        if (room instanceof RoomMongo roomMongo) {
            return roomMongo;
        } else {
            RoomMongo roomMongo = new RoomMongo();

            roomMongo.setId(room.getId());
            roomMongo.setName(room.getName());
            roomMongo.setCapacity(room.getCapacity());
            roomMongo.setContains(room.getContains().stream().map(Migration::containsToMongo).collect(Collectors.toSet()));

            return roomMongo;
        }
    }

    public static ContainsMongo containsToMongo(Contains contains) {
        if (contains instanceof ContainsMongo containsMongo) {
            return containsMongo;
        } else {
            ContainsMongo containsMongo = new ContainsMongo();

            containsMongo.setId(contains.getId());
            containsMongo.setQuantity(contains.getQuantity());
            containsMongo.setDeliveryDate(contains.getDeliveryDate());
            containsMongo.setBook(bookToMongo(contains.getBook()));

            return containsMongo;
        }
    }

    public static BookMongo bookToMongo(Book book) {
        if (book instanceof BookMongo bookMongo) {
            return bookMongo;
        } else {
            BookMongo bookMongo = new BookMongo();

            bookMongo.setId(book.getId());
            bookMongo.setName(book.getName());
            bookMongo.setAmountPages(book.getAmountPages());

            return bookMongo;
        }
    }

    public static UserMongo userToMongo(User user) {
        if (user instanceof Employee employee) {
            return employeeToMongo(employee);
        } else if (user instanceof Customer customer) {
            return customerToMongo(customer);
        } else {
            throw new InvalidDataException("type not defined");
        }
    }

    public static EmployeeMongo employeeToMongo(Employee employee) {
        if (employee instanceof EmployeeMongo employeeMongo) {
            return employeeMongo;
        } else {
            EmployeeMongo employeeMongo = new EmployeeMongo();

            employeeMongo.setId(employee.getId());
            employeeMongo.setSalary(employee.getSalary());
            employeeMongo.setHiringDate(employee.getHiringDate());
            employeeMongo.setEmail(employee.getEmail());

            return employeeMongo;
        }
    }

    public static CustomerMongo customerToMongo(Customer customer) {
        if (customer instanceof CustomerMongo customerMongo) {
            return customerMongo;
        } else {
            CustomerMongo customerMongo = new CustomerMongo();

            customerMongo.setId(customer.getId());
            customerMongo.setName(customer.getName());
            customerMongo.setEmail(customer.getEmail());
            customerMongo.setPhoneNumber(customer.getPhoneNumber());
            customerMongo.setRegistrationDate(customer.getRegistrationDate());

            return customerMongo;
        }
    }

    public static BorrowsMongo borrowsToMongo(Borrows borrows) {
        if (borrows instanceof BorrowsMongo borrowsMongo) {
            return borrowsMongo;
        } else {
            BorrowsMongo borrowsMongo = new BorrowsMongo();

            borrowsMongo.setId(borrows.getId());
            borrowsMongo.setBook(bookToMongo(borrows.getBook()));
            borrowsMongo.setRoom(((BorrowsMySql)borrows).getRoom());
            borrowsMongo.setUser(userToMongo(((BorrowsMySql)borrows).getUser()));
            borrowsMongo.setStartDate(borrows.getStartDate());
            borrowsMongo.setEndDate(borrows.getEndDate());

            return borrowsMongo;
        }
    }
}
