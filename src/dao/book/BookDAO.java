package dao.book;


import bean.Book;
import org.mongodb.morphia.dao.DAO;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public interface BookDAO extends DAO <Book,String>{

    /**
     * get a book details using its ISBN
     *
     * @return Book
     */
    public Book getBookDetails(String ISBN);





}
