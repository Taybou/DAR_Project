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
import java.io.IOException;
import java.util.HashMap;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();

        String userName = req.getParameter("userName");
        String relatedToUser = req.getParameter("relatedTo");
        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) resp);

//        @url(/api/users?userName=something) : Get one user
        if (userName!= null) {
            User user = userDAO.getUserByUserName(userName);
            jsonResponse.sendJsonObject(user);
        }
//        @url(/api/users?relatedTo=some user) : Get users who have messaged "some user"
        else if (relatedToUser != null) {
            MessageDAO messageDAO = new MessageDAO();
            List<Message> messages = messageDAO.getLatestMessagesByUser(userDAO.getUserByUserName(relatedToUser));
            HashMap<String, User> result = new HashMap<>();
            String temp_username;
            for(Message msg : messages) {
                if(msg.getFrom().getUserName().equals(relatedToUser)) {
                    temp_username = msg.getTo().getUserName();
                } else {
                    temp_username = msg.getFrom().getUserName();
                }
                if(!result.containsKey(temp_username)) {
                    result.put(temp_username, userDAO.getUserByUserName(temp_username));
                }
            }
            jsonResponse.sendJsonObject(result);

        }
//        @url(/api/users) : Get All users
        else {
                List<User> users = userDAO.getAllUsers();
                jsonResponse.sendJsonObject(users);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        UserDAO userDAO = new UserDAO();
        User user = request.getJsonObject(User.class);

        Map<String, Object> objectMap = request.getJsonMap();
        System.out.println(objectMap.get("userName"));
//        If user exists it will be updated
        userDAO.addUser(user);
        response.sendJsonObject(user);
    }
}
