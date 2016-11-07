package servlets;

import bean.*;
import bean.googlebooks.GoogleBook;
import dao.DAOFactory;
import dao.alert.AlertDAO;
import dao.message.MessageDAO;
import dao.message.NotificationDAO;
import dao.user.UserDAO;
import errors.Error;
import filters.AuthorizationFilter;
import servlets.wrappers.HttpServletJsonRequest;
import servlets.wrappers.HttpServletJsonResponse;
import validators.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

/*
* This is an Example of How we can implement a REST API using Servlets
* It is just an example, the Users should not be created and queried using this servlet
* */

public class UsersServlet extends HttpServlet {

    private UserDAO userDAO;
    private AlertDAO alertDAO;
    private NotificationDAO notificationDAO;

    public void init() {
        userDAO = DAOFactory.getUserDAO();
        alertDAO = DAOFactory.getAlertDAO();
        notificationDAO = DAOFactory.getNotificationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = ((HttpServletJsonResponse) resp);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        String action = req.getParameter("action");
        String userName = req.getParameter("userName");
        String relatedToUser = req.getParameter("relatedTo");

        if (action != null) {
            switch (action) {
                case "getBooks":
                    List<GoogleBook> booksList = userDAO.getBooks(userName);
                    response.sendJsonObject(booksList);
                    break;
                case "getProfile":
                    response.sendJsonObject(UserPublicProfile.getUserProfile(user));
                    break;
                case "viewProfile":
                    User user1 = userDAO.getUserByUserName(userName);
                    response.sendJsonObject(UserPublicProfile.getUserProfile(user1));
                    break;
                default:
                    response.sendJsonError(new Error("Requete non valide"), 400);
                    break;
            }
        }
        else {
            //@url(/api/users) : Get All users
            List<User> users = userDAO.getAllUsers();
            response.sendJsonObject(users);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        String action = req.getParameter("action");
        String isbn = request.getParameter("isbn");

        switch (action) {
            case "updateProfile":
                Map<String, Object> userData = request.getJsonMap();
                User updatedUser = new User(user);

                updatedUser.setLastName((String)userData.get("lastName"));
                updatedUser.setFirstName((String)userData.get("firstName"));
                updatedUser.setAddress((String)userData.get("address"));
                updatedUser.setGender((String) userData.get("gender"));
                updatedUser.setDescription((String)userData.get("description"));

                Map<String, Object> errors = new HashMap<>();
                UserValidator.validateUser(updatedUser, errors);

                if (errors.isEmpty()) {
                    userDAO.addUser(updatedUser);
                    session.setAttribute(AuthorizationFilter.USER_SESSION, updatedUser);
                    response.sendJsonObject(UserPublicProfile.getUserProfile(updatedUser));
                }
                else {
                    response.sendJsonMapError(errors, 400);
                }
                break;
            case "addBook":
                user.getBooksIsbnList().add(isbn);
                userDAO.addUser(user);
                this.createAddBookNotification(user, isbn);
                response.sendJsonObject(UserPublicProfile.getUserProfile(user));
                break;
            case "deleteBook":
                user.getBooksIsbnList().remove(isbn);
                userDAO.addUser(user);
                response.sendJsonObject(UserPublicProfile.getUserProfile(user));
                break;
            default:
                response.sendJsonError(new Error("Requete non valide"), 400);
                break;
        }
    }

    private void createAddBookNotification(User user, String isbn) {

        List<Alert> alerts = alertDAO.getAlertByISBN(isbn);

        for (Alert alert: alerts) {
            Notification notification = new Notification();
            User user1 = alert.getUser();
            notification.setUser(user1);
            notification.setFrom(user);
            notification.setBookISBN(isbn);
            notification.setType(Notification.Type.Alert);
            notificationDAO.addNotification(notification);
        }
    }
}
