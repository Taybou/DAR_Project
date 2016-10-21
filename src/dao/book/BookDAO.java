package dao.book;


import bean.User;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public interface BookDAO {

    /**
     * get a book details using its ISBN
     *
     * @return Book
     */
    public String getBookDetails(String ISBN);

    public String findBooksByTitle_Author_ISBN(String query);

    public void addBooks(User user);
}
