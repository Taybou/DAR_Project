package servlets;

import bean.User;
import dao.DAOFactory;
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
import java.util.Map;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;
        HttpSession session = request.getSession();

        Map<String, Object> signupPost = request.getJsonMap();
        // We will not use server side validation on SignUp post data
        UserDAO userDAO = DAOFactory.getUserDAO();
        //Checking if user exists
        User user = userDAO.getUserByUserName((String) signupPost.get("userName"));

        if (user != null) {
            //If exists : throw error
            session.setAttribute(AuthorizationFilter.USER_SESSION, null);
            Error error = new Error("The user name is taken");
            response.sendJsonError(error, 400);
        }
        else {
            //If not exists : create a user
            user = new User();
            user.setUserName((String) signupPost.get("userName"));
            user.setPassword((String) signupPost.get("password"));
            user.setEmail((String) signupPost.get("email"));
            user.setFirstName((String) signupPost.get("firstName"));
            user.setLastName((String) signupPost.get("lastName"));
            // Add the user:
            userDAO.addUser(user);
            // Creating a session for the user (if you want to connect the user on signup)
            //session.setAttribute(AuthorizationFilter.USER_SESSION, user);
            // Return the created User:
            response.sendJsonObject(user.getUserName());
        }
    }
}
