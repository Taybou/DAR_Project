package bean;

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
    @Id
    private String id;

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

    enum Type {
        Message,
        Exchange
    }
}
