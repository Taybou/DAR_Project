package dao.alert;

import bean.Alert;
import bean.User;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * BooXchange Project
 * Created by Al on 07-Nov-16.
 */
public class AlertDAO {

    private Datastore datastore;

    public AlertDAO() {
        datastore = MorphiaDataStore.getDataStore();
    }

    public Alert addAlert(Alert alert) {
        datastore.save(alert);
        return alert;
    }

    public Alert getAlert(User user, String bookISBN) {
        return datastore.createQuery(Alert.class)
                .field("user").equal(user)
                .field("bookISBN").equal(bookISBN)
                .asList().get(0);
    }

    public List<Alert> getUserAlerts(User user) {
        return datastore.createQuery(Alert.class)
                .field("user").equal(user)
                .asList();
    }

    public void deleteAlert(User user, String bookISBN) {
        Query<Alert> alertQuery = datastore.createQuery(Alert.class)
                .field("user").equal(user)
                .field("bookISBN").equal(bookISBN);
        datastore.delete(alertQuery);
    }


    public List<Alert> getAlertByISBN(String bookISBN) {
        return datastore.createQuery(Alert.class)
                .field("bookISBN").equal(bookISBN)
                .asList();
    }
}
