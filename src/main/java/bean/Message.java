package bean;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kadao on 16/10/2016.
 */

@Entity("messages")
public class Message {
    @Id String id;
    Date timeStamp;
    @Reference User from;
    @Reference User to;
    String content;

    public Message() {
    }

    public Message(User from, User to, String content) {
        this.timeStamp = (Calendar.getInstance()).getTime();
        this.from = from;
        this.to = to;
        this.content = content; // TODO maybe parse it
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
}
