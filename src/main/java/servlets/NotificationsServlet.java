package servlets;

import bean.Message;
import bean.Notification;
import bean.User;
import dao.DAOFactory;
import dao.message.MessageDAO;
import dao.message.NotificationDAO;
import dao.user.UserDAO;
import errors.Error;
import filters.AuthorizationFilter;
import servlets.wrappers.HttpServletJsonRequest;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kadao on 05/11/2016.
 */
public class NotificationsServlet extends HttpServlet {

    private NotificationDAO notificationDAO ;

    @Override
    public void init() throws ServletException {
        notificationDAO = DAOFactory.getNotificationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);
        String type = req.getParameter("type");
        String from = req.getParameter("from");

        HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(resp);


        switch (type) {
            case "alert":
                jsonResponse.sendJsonObject(notificationDAO.getNotifications(user, type));
                break;
            case "exchange":
                jsonResponse.sendError(500, "Not implemented");
                break;
            case "message":
                if (from != null) {
                    UserDAO userDAO = new UserDAO();
                    User fromu = userDAO.getUserByUserName(from);
                    if (fromu != null) {
                        long result = notificationDAO.getNotificationsNumber(user, type, fromu);
                        jsonResponse.sendJsonObject(result);
                    } else {
                        jsonResponse.sendError(404, "from User Not Found");
                    }
                }
                else {
                    long result = notificationDAO.getNotificationsNumber(user, type);
                    jsonResponse.sendJsonObject(result);
                }
                break;
            default:
                jsonResponse.sendError(400, "requete non valide");
                break;
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        String notificationId = request.getParameter("id");

        if (notificationId != null) {
            notificationDAO.deleteNotification(notificationId);
            response.sendJsonObject("Notification supprimée");
        }
        else {
            response.sendJsonError(new Error("Requete non valide"), 400);
        }
    }
}
