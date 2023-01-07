package at.innotechnologies.backend.book;

import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMySql;

import java.util.HashSet;
import java.util.Set;

public interface Book {
    Integer getId();
    String getName();
    Integer getAmountPages();

    /*
    Set<Contains> contains = new HashSet<>();
    Set<Borrows> borrowed = new HashSet<>();

     */

    void setId(Integer id);
    void setName(String name);
    void setAmountPages(Integer amountPages);

    Set<Contains> getContains();
    Set<Borrows> getBorrowed();
}
