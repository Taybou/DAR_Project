package dao.book;

import bean.User;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public class BookDAOImpl implements BookDAO {

    private Datastore datastore = null;
    private BookRestService bookRestService = null;

    public BookDAOImpl() {
        datastore = MorphiaDataStore.getDataStore();
        bookRestService = new BookRestService();

    }

    @Override
    public String getBookDetails(String ISBN) {

        try {
            return bookRestService.getBookDetails(ISBN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String findBooksByTitle_Author_ISBN(String query) {
        try {
            return bookRestService.findBooksByTitle_Author_ISBN(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addBooks(User user) {
        // Add books of the user
        // by adding a list of isbn to the user owner

        Query<User> query = datastore.createQuery(User.class).field("userName").equal(user.getUserName());
        List list = query.get().getBooksIsbnList();
        if (list == null) {
            list = new ArrayList();
        }
        for (int i = 0; i < user.getBooksIsbnList().size(); i++) {
            list.add(user.getBooksIsbnList().get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            UpdateOperations<User> userUpdateOperations = datastore.createUpdateOperations(User.class).add("booksIsbnList", list.get(i));
            datastore.update(query, userUpdateOperations);
        }
        // System.out.println("bbbb ");

    }

}
