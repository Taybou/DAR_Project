package servlets;

import bean.Message;
import bean.User;
import dao.message.MessageDAO;
import dao.message.NotificationDAO;
import dao.user.UserDAO;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotificationDAO notificationDAO = new NotificationDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);
        String type = req.getParameter("type");
        String number = req.getParameter("number");
        String from = req.getParameter("from");

        HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(resp);

        if (type != null) {

            if (user != null) {

                if (from != null) {
                    UserDAO userDAO = new UserDAO();
                    User fromu = userDAO.getUserByUserName(from);
                    if (fromu != null) {
                        long result = notificationDAO.getNotificationsNumber(user, type, fromu);
                        jsonResponse.sendJsonObject(result);
                    } else {
                        jsonResponse.sendError(404, "from User Not Found");
                    }

                } else {
                    long result = notificationDAO.getNotificationsNumber(user, type);
                    jsonResponse.sendJsonObject(result);
                }

            } else {
                jsonResponse.sendError(404, "User Not Found");
            }
        } else {
            jsonResponse.sendError(400, "Bad Request - this get method takes \"userName\" as an input");
        }
    }

}
