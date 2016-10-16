package dao.book;


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


}
