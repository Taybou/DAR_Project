package bean;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 14/10/2016.
 */
@Entity("Book")
public class Book {
    @Id
    private String isbn;

    private String title;
    private String author;
    private String language;
    private String category;
    private String thumbnail;
    private String average_rating;
    private String desciption ;




    public Book(String isbn, String title, String author, String language, String category, String thumbnail, String average_rating, String desciption) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.language = language;
        this.category = category;
        this.thumbnail = thumbnail;
        this.average_rating = average_rating;
        this.desciption = desciption;
    }
    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
