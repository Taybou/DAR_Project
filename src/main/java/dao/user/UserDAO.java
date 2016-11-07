package dao.user;

import bean.User;
import bean.googlebooks.GoogleBook;
import dao.MorphiaDataStore;
import dao.book.BookAPIAccess;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * BooXchange Project
 * Created by Al on 07-Oct-16.
 */

/*
* This a DataAccess class for the Users
* It is not a complete class yet
* */
public class UserDAO {

    private Datastore datastore;
    private BookAPIAccess bookAPIAccess;

    public UserDAO() {
        datastore = MorphiaDataStore.getDataStore();
        bookAPIAccess = new BookAPIAccess();
    }

    public User addUser(User user) {
        /*
        * Behaviour of datastore.save(user) :
        * Add a user if does not exist.
        * Update it if it exists.
        * */
        datastore.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        Query<User> userQuery = datastore.createQuery(User.class);
        return userQuery.asList();
    }

    public User getUserByUserName(String userName) {
        return datastore.get(User.class, userName);
    }

    public List<User> getUserByISBN(String isbn) {

        Query<User> userQuery = datastore.createQuery(User.class);

        userQuery.field("booksIsbnList").contains(isbn);

        return userQuery.asList();
    }

    public void addBook(User user) {
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
    }

    public void deleteBook(String userName, String isbn) {
        Query<User> query = datastore.createQuery(User.class).field("userName").equal(userName);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < query.get().getBooksIsbnList().size(); i++) {
            if (!query.get().getBooksIsbnList().get(i).equals(isbn)) {
                list.add(query.get().getBooksIsbnList().get(i));
            }
        }
        UpdateOperations<User> updateOperations = datastore.createUpdateOperations(User.class).set("booksIsbnList", list);
        datastore.update(query, updateOperations);

    }

    public List<GoogleBook> getBooks(String userName) {
        List<GoogleBook> booksList = new ArrayList<>();

        Query<User> query = datastore.createQuery(User.class).field("userName").equal(userName);
        List<String> list = query.get().getBooksIsbnList();
        for (int i = 0; i < list.size(); i++) {
            GoogleBook book = bookAPIAccess.getBook(list.get(i));
            if (book != null) {
                book.getVolumeInfo().getIndustryIdentifiers().get(0).setType("ISBN_13");
                book.getVolumeInfo().getIndustryIdentifiers().get(0).setIdentifier(list.get(i));
                booksList.add(book);
            }
        }

        return booksList;
    }


}
