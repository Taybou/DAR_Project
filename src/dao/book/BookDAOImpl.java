package dao.book;

import bean.Book;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public class BookDAOImpl implements BookDAO {

    private Datastore datastore = null;

    public BookDAOImpl() {

        datastore = MorphiaDataStore.getDataStore();
    }

    @Override
    public Book getBookDetails(String ISBN) {

        BookRestService bookRestService = new BookRestService();
        try {
            return bookRestService.getBookDetails(ISBN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
