package dao;

import dao.user.UserDAO;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
final public class DAOFactory {

    static public UserDAO getUserDAO() {
        return new UserDAO();
    }
}
