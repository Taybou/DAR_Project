package servlets;

import bean.Message;
import bean.Notification;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kadao on 16/10/2016.
 */
public class MessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageDAO messageDAO = new MessageDAO();
        UserDAO userDAO = new UserDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        String type = req.getParameter("type");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(resp);

        if (type != null && user != null) {
            switch (type) {
                case "messages":
                    String receiver = req.getParameter("receiver");
                    if (receiver != null) {
                        User recUser = userDAO.getUserByUserName(receiver);


                        notificationDAO.deleteNotificationsUser(user, "message", recUser);

                        if (user != null) {
                            List<Message> messages = messageDAO.getMessagesByUsers(user, recUser);


                            jsonResponse.sendJsonObject(messages);
                        } else {
                            jsonResponse.sendError(404, "User Not Found");
                        }
                    } else {
                        jsonResponse.sendError(400, "Bad Request - this get method takes \"receiver\" as an input");
                    }
                    break;
                case "contacts":

                    List<Message> messages = messageDAO.getLatestMessagesByUser(user);
                    HashMap<String, User> users = new HashMap<>();
                    List<User> result = new ArrayList<>();
                    String temp_username;
                    for (Message msg : messages) {
                        if (msg.getFrom().getUserName().equals(user.getUserName())) {
                            temp_username = msg.getTo().getUserName();
                        } else {
                            temp_username = msg.getFrom().getUserName();
                        }
                        if (!users.containsKey(temp_username)) {
                            users.put(temp_username, userDAO.getUserByUserName(temp_username));
                            result.add(userDAO.getUserByUserName(temp_username));
                        }
                    }
                    jsonResponse.sendJsonObject(result);
                    break;
                case "contactsWithNew":
                    List<Notification> CWN = notificationDAO.getNotifications(user, "message");
                    HashMap<String, Long> contacts = new HashMap<>();
                    for (Notification notif : CWN) {
                        contacts.put(notif.getFrom().getUserName(), notificationDAO.getNotificationsNumber(user, "message", notif.getFrom()));
                    }
                    jsonResponse.sendJsonObject(contacts);
                    break;
                default:
                    jsonResponse.sendError(400, "Bad Request - \"type\" could be \"messages\" or \"contacts\"");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletJsonRequest request = (HttpServletJsonRequest) req;

        Map<String, Object> messagePost = request.getJsonMap();
        //System.out.println(messagePost);
        MessageDAO messageDAO = new MessageDAO();
        UserDAO userDAO = new UserDAO();
        String to = (String) messagePost.get("receiver");
        String content = (String) messagePost.get("msg");
        HttpServletJsonResponse jsonResp = new HttpServletJsonResponse(resp);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        if (to != null && content != null) {
            User userTo = userDAO.getUserByUserName(to);
            User userFrom = user;
            if(userFrom != null && userTo != null) {
                messageDAO.addMessage(new Message(userFrom, userTo, content));

                NotificationDAO notificationDAO = new NotificationDAO();
                notificationDAO.addNotification(new Notification(userTo, userFrom, "message"));

                jsonResp.sendJsonObject("message delivered");
            } else {
                jsonResp.sendError(404, "User(s) Not Found");
            }

        }  else {
            jsonResp.sendError(400,"Bad Request ------------------------------------------------ ");
        }
        resp = jsonResp;
    }
}
