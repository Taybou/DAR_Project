package dao;

import bean.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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

    private Datastore datastore = null;

    public UserDAO() {
        datastore = MorphiaDataStore.getDataStore();
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
}
