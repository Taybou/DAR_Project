package dao;

import beans.Book;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public class BookDAOImpl  extends BasicDAO<Book, String> implements BookDAO {

    public BookDAOImpl(Class<Book> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public Book getBookDetails(String ISBN) {
        return null;
    }
}
