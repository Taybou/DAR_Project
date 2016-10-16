package dao.book;

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
    public String getBookDetails(String ISBN) {

        BookRestService bookRestService = new BookRestService();
        try {
            return bookRestService.getBookDetails(ISBN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
