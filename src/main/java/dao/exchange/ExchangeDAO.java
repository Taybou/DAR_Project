package dao.exchange;

import bean.Exchange;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 04/11/2016.
 */
public class ExchangeDAO {
    private Datastore datastore;

    public ExchangeDAO() {
        datastore = MorphiaDataStore.getDataStore();
    }

    public Exchange addExchange(Exchange exchange) {
        datastore.save(exchange);
        return exchange;
    }

    public List<Exchange> getExchangeSubmitted(String userOwner) {
        List<Exchange> list = datastore.createQuery(Exchange.class).field("user1").equal(userOwner).asList();
        return list;
    }

    public List<Exchange> getExchangeNotified(String userAsked) {
        List<Exchange> list = datastore.createQuery(Exchange.class).field("user2").equal(userAsked).asList();
        return list;
    }

    public void acceptExchange(String id) {
        Query query = datastore.createQuery(Exchange.class).field("_id").equal(id);
        UpdateOperations<Exchange> exchangeUpdateOperations = datastore.createUpdateOperations(Exchange.class).set("isAccepted", true);
        datastore.update(query, exchangeUpdateOperations);
    }

}
