package dao.book;


import bean.googlebooks.GoogleBook;

import java.util.List;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 2016-10-15.
 */
public interface BookDAO {

    /**
     * get a book details using its isbn
     *
     * @return GoogleBook
     */
    public GoogleBook getBookDetails(String isbn);

    /**
     * find books by query
     *
     * @return List<GoogleBook>
     */
    public List<GoogleBook> findBooks(String query);

    /**
     * find books that are owned by actual users in BooXchange
     *
     * @return List<GoogleBook>
     */

    public List<GoogleBook> findOwnedBooks(String query);
}
