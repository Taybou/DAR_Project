package dao.book;

import bean.Book;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */

public class BookRestService {

    private static final String FORMAT = "xml";
    private static final String ISBN = "ISBN";
    private static final String APIKEY = "SqPGSUll3Xu1ysNbFEXtng";

    private HttpResponse httpResponse;
    private int statusCode;
    private HttpEntity httpEntity;
    private HttpGet httpGet;
    private URIBuilder uriBuilder;
    private URI uri;

    public Book getBookDetails(String isbn) throws Exception {
        String result = "Rien0";
        HttpClient httpClient = HttpClientBuilder.create().build();

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

        return getBookObject(result);
    }

    public String findBooksByTitle_Author_ISBN(String query) throws Exception {
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();

        try {
            uriBuilder = new URIBuilder();
            uriBuilder.setScheme("https").setHost("www.goodreads.com").setPath("/book/isbn/" + query)
                    .setParameter("key", API_KEY);
            uri = uriBuilder.build();
            httpGet = new HttpGet(uri);

            httpResponse = httpClient.execute(httpGet);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return getBookObject(result).getIsbn();

    }

    private Book getBookObject(String xmlFile) {

        String isbn = null;
        String title = null;
        String author = null;
        String language = null;
        String category = null;
        String thumbnail = null;
        String average_rating = null ;
        String description = null;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new InputSource(new ByteArrayInputStream(xmlFile.getBytes("utf-8"))));
            final Element root = document.getDocumentElement();
            final NodeList racineNoeuds = root.getChildNodes();
            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals("book")) {

                    final Element book = (Element) racineNoeuds.item(i);
                    title = book.getElementsByTagName("title").item(0).getTextContent();

                    isbn = book.getElementsByTagName("isbn").item(0).getTextContent();

                    language = book.getElementsByTagName("language_code").item(0).getTextContent();

                    thumbnail = formatUrl(book.getElementsByTagName("image_url").item(0).getTextContent());

                    description = book.getElementsByTagName("description").item(0).getTextContent();

                    average_rating = book.getElementsByTagName("average_rating").item(0).getTextContent();

                    final NodeList authorNode = book.getChildNodes();
                    for (int j = 0; j < authorNode.getLength(); j++) {
                        if (authorNode.
                                item(j).
                                getNodeType() == Node.
                                ELEMENT_NODE && authorNode
                                .item(j)
                                .getNodeName()
                                .equals("authors")) {
                            final Element authorE = (Element) authorNode.item(j);
                            author = authorE.getElementsByTagName("name").item(0).getTextContent();
                        }
                    }

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Book bookO = new Book(isbn, title, author, language, category, thumbnail,average_rating,description);
        return bookO;
    }

    private String  formatUrl (String url){
        int ind = url.lastIndexOf("m");
        url = new StringBuilder(url).replace(ind, ind+1,"l").toString();
        return  url;
    }
}