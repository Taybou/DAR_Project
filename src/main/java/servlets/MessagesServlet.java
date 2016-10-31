package servlets;

import bean.Message;
import bean.User;
import dao.message.MessageDAO;
import dao.user.UserDAO;
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
 * Created by kadao on 16/10/2016.
 */
public class MessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageDAO messageDAO = new MessageDAO();
        UserDAO userDAO = new UserDAO();

        String sender = req.getParameter("sender");
        String receiver = req.getParameter("receiver");

        HttpServletJsonResponse jsonResponse /*= ((HttpServletJsonResponse) resp);*/ = new HttpServletJsonResponse(resp);

        if (sender!= null && receiver != null ) {
            User user = userDAO.getUserByUserName(sender);
            User recUser = userDAO.getUserByUserName(receiver);
            if(user != null) {
                List<Message> messages = messageDAO.getMessagesByUsers(user, recUser);
                jsonResponse.sendJsonObject(messages);
            } else {
                jsonResponse.sendError(404, "User Not Found");
            }
        } else {
            jsonResponse.sendError(400, "Bad Request - this get method takes \"userName\" as an input");
        }
        resp = jsonResponse;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        //HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        Map<String, Object> messagePost = request.getJsonMap();
        System.out.println(messagePost);
        MessageDAO messageDAO = new MessageDAO();
        UserDAO userDAO = new UserDAO();
        String to = (String) messagePost.get("receiver");
        String from = (String) messagePost.get("sender");
        String content = (String) messagePost.get("msg");
        HttpServletJsonResponse jsonResp = new HttpServletJsonResponse(resp);

        if(to != null && from != null && content != null) {
            User userTo = userDAO.getUserByUserName(to);
            User userFrom = userDAO.getUserByUserName(from);
            if(userFrom != null && userTo != null) {
                messageDAO.addMessage(new Message(userFrom, userTo, content));
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
