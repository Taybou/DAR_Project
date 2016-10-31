package dao.message;

import bean.Message;
import bean.User;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by kadao on 16/10/2016.
 */
public class MessageDAO {

    private Datastore datastore = null;

    public MessageDAO() {
        datastore = MorphiaDataStore.getDataStore();
    }

    public Message addMessage(Message msg) {
        datastore.save(msg);
        return msg;
    }

    public List<Message> getMessagesByUsers(User user1, User user2) {
        Query<Message> messageQuery = datastore.createQuery(Message.class);
        messageQuery.or(
                messageQuery.and(
                        messageQuery.criteria("to").equal(user1),
                        messageQuery.criteria("from").equal(user2)
                ),
                messageQuery.and(
                        messageQuery.criteria("to").equal(user2),
                        messageQuery.criteria("from").equal(user1)
                )
        );
        messageQuery.order("-timeStamp");
        return messageQuery.asList();
    }

    public List<Message> getLatestMessagesByUser(User user) {
        Query<Message> messageQuery = datastore.createQuery(Message.class);
        messageQuery.or(messageQuery.criteria("to").equal(user), messageQuery.criteria("from").equal(user));
        messageQuery.order("-timeStamp");
        messageQuery.limit(20);
        return messageQuery.asList();
    }

}
