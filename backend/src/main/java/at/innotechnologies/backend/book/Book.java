package at.innotechnologies.backend.book;

public interface Book {
    String getId();
    String getName();
    Integer getAmountPages();


    void setId(String id);
    void setName(String name);
    void setAmountPages(Integer amountPages);
}
