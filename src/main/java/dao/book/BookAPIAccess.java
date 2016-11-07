package dao.book;

import bean.googlebooks.BookSearchResult;
import bean.googlebooks.GoogleBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * BooXchange Project
 * Created by Al on 24-Oct-16.
 */
public class BookAPIAccess {

    public static String GOOGLE_BOOKS_URI = "https://www.googleapis.com/books/v1/volumes";

    //private ObjectMapper objectMapper;

    public BookAPIAccess() {

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public GoogleBook getBook(String isbn) {

        try {
            HttpResponse<BookSearchResult> response = Unirest.get(GOOGLE_BOOKS_URI)
                    .header("cache-control", "no-cache")
                    .queryString("q", "isbn:" + isbn)
                    .asObject(BookSearchResult.class);

            BookSearchResult result = response.getBody();
            if (result == null || result.getTotalItems() == 0) {
                return null;
            }
            else {
                return result.getItems().get(0);
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
            BookSearchResult result = response.getBody();
            return (ArrayList<GoogleBook>) result.getItems();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
