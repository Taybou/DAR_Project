package dao.message;

import bean.Notification;
import bean.User;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by kadao on 05/11/2016.
 */
public class NotificationDAO {

    private Datastore datastore = null;

    public NotificationDAO() {
        datastore = MorphiaDataStore.getDataStore();
    }

    public Notification addNotification(Notification notif) {
        datastore.save(notif);
        return notif;
    }

    public long getNotificationsNumber(User user, String type) {
        Query<Notification> notificationQuery = datastore.createQuery(Notification.class);
        notificationQuery.and(
                notificationQuery.criteria("user").equal(user),
                notificationQuery.criteria("type").equal(Notification.toType(type))
        );
        return notificationQuery.countAll();
    }

    public long getNotificationsNumber(User user, String type, User from) {
        Query<Notification> notificationQuery = datastore.createQuery(Notification.class);
        notificationQuery.and(
                notificationQuery.criteria("user").equal(user),
                notificationQuery.criteria("type").equal(Notification.toType(type)),
                notificationQuery.criteria("from").equal(from)
        );
        return notificationQuery.countAll();
    }

    public List<Notification> getNotifications(User user, String type) {
        Query<Notification> notificationQuery = datastore.createQuery(Notification.class);
        notificationQuery.and(
                notificationQuery.criteria("user").equal(user),
                notificationQuery.criteria("type").equal(Notification.toType(type))
        );
        return notificationQuery.asList();
    }

    public void deleteNotificationsUser(User user, String type) {
        Query<Notification> notificationQuery = datastore.createQuery(Notification.class);
        notificationQuery.and(
                notificationQuery.criteria("user").equal(user),
                notificationQuery.criteria("type").equal(Notification.toType(type))
        );
        datastore.delete(notificationQuery);
    }

    public void deleteNotificationsUser(User user, String type, User from) {
        Query<Notification> notificationQuery = datastore.createQuery(Notification.class);
        notificationQuery.and(
                notificationQuery.criteria("user").equal(user),
                notificationQuery.criteria("from").equal(from),
                notificationQuery.criteria("type").equal(Notification.toType(type))
        );
        datastore.delete(notificationQuery);
    }
}
