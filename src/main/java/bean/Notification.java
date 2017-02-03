package bean;

import org.hibernate.validator.constraints.SafeHtml;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by kadao on 05/11/2016.
 */
@Entity("notifications")
public class Notification {

    @Reference
    User user;
    @Reference
    User from;
    Type type;
    @SafeHtml(message = "Nom d'utilisateur non valide")
    private String bookISBN;
    @Id
    private String notificationId;

    public Notification() {
    }

    public Notification(User user, User from, Type type) {
        this.user = user;
        this.type = type;
        this.from = from;
    }

    public Notification(User user, User from, String type) {
        this.user = user;
        this.type = toType(type);
        this.from = from;
    }

    public static Type toType(String str) {
        if (str.compareToIgnoreCase("message") == 0) {
            return Type.Message;
        } else if (str.compareToIgnoreCase("exchange") == 0) {
            return Type.Exchange;
        } else if (str.compareToIgnoreCase("alert") == 0) {
            return Type.Alert;
        } else return null;
    }

    public User getUser() {
        return user;
    }

    public Type getType() {
        return type;
    }

    public User getFrom() {
        return from;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public enum Type {
        Message,
        Exchange,
        Alert
    }
}
