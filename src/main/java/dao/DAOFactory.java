package dao;

import dao.alert.AlertDAO;
import dao.book.BookDAOImpl;
import dao.exchange.ExchangeDAO;
import dao.message.NotificationDAO;
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

    public static AlertDAO getAlertDAO() { return new AlertDAO();}

    public static NotificationDAO getNotificationDAO() {return new NotificationDAO();};
}
