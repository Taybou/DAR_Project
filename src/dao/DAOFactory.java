package dao;

import dao.book.BookDAOImpl;
import dao.user.UserDAO;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
final public class DAOFactory {

    static public UserDAO getUserDAO() {
        return new UserDAO();
    }

    public static BookDAOImpl getBookDAO() {
        return new BookDAOImpl();
    }
}
