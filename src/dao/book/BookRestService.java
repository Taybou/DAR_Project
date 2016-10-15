package dao.book;



import bean.Book;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.apache.deltaspike.core.api.config.ConfigProperty;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */

public class BookRestService {

    @Inject
    @ConfigProperty(name = "GOODREADS_API_KEY")
    private String apikey;

    private Client client;
    private WebTarget target;


    public Book getBookDetails( String isbn){


return null;
    }
}
