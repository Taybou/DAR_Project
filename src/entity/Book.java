package entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * BooXchange Project
 * Created by Xo on 14/10/2016.
 */
@Entity("Book")
public class Book {
    @Id
    private String isbn;

    private String title;
    private String author;
    private String language;
    private String category;

    public Book(String isbn, String title, String author, String language, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.language = language;
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
