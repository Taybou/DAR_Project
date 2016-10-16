package dao.book;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */

public class BookRestService {

    private static final String FORMAT = "xml";
    private static final String ISBN = "ISBN";
    private static final String APIKEY = "SqPGSUll3Xu1ysNbFEXtng";

    public String getBookDetails(String isbn) throws Exception {
        String result = "Rien0";
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            HttpGet getRequest = new HttpGet("https://www.goodreads.com/book/isbn/" + isbn + "?key=" + APIKEY);
            getRequest.addHeader("accept", "application/xml");
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);

            result = apiOutput;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return result;
    }
}
