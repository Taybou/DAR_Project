package dao.book;

import bean.googlebooks.BookSearchResult;
import bean.googlebooks.GoogleBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import unirest.config.UnirestConfig;

import java.io.IOException;
import java.util.ArrayList;

/**
 * BooXchange Project
 * Created by Al on 24-Oct-16.
 */
public class BookAPIAccess {

    private static BookAPIAccess instance = null;
    public static String GOOGLE_BOOKS_URI = "https://www.googleapis.com/books/v1/volumes";

    //private ObjectMapper objectMapper;

    private BookAPIAccess() {
        UnirestConfig.init();
    }

    public static BookAPIAccess getBookAPIAccess() {

        if (instance != null) {
            return instance;
        }
        else {
            instance = new BookAPIAccess();
            return instance;
        }
    }

    public GoogleBook getBook(String isbn) {

        try {
            HttpResponse<BookSearchResult> response = Unirest.get(GOOGLE_BOOKS_URI)
                    .header("cache-control", "no-cache")
                    .queryString("q", "isbn:" + isbn)
                    .asObject(BookSearchResult.class);

            if (response.getStatus() == 200) {
                BookSearchResult result = response.getBody();
                if (result == null || result.getTotalItems() == 0) {
                    return null;
                }
                else {
                    return result.getItems().get(0);
                }
            }
            else {
                return null;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<GoogleBook> findBooks(String query) {

        try {
            HttpResponse<BookSearchResult> response = Unirest.get(GOOGLE_BOOKS_URI)
                    .header("cache-control", "no-cache")
                    .queryString("q", query)
                    .queryString("maxResults", 20)
                    .asObject(BookSearchResult.class);

            if (response.getStatus() == 200) {
                BookSearchResult result = response.getBody();
                return (ArrayList<GoogleBook>) result.getItems();
            }
            else {
                return new ArrayList<>();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
