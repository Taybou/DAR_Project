package dao;

import dao.book.BookDAOImpl;
import dao.exchange.ExchangeDAO;
import dao.user.UserDAO;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
public final class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAO();
    }

    public static ExchangeDAO getExchangeDAO() {
        return new ExchangeDAO();
    }

    public static BookDAOImpl getBookDAO() {
        return new BookDAOImpl();
    }
}
