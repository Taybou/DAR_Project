package servlets;

import bean.User;
import dao.DAOFactory;
import dao.user.UserDAO;
import errors.Error;
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

        Map<String, Object> signupPost = request.getJsonMap();

        UserDAO userDAO = DAOFactory.getUserDAO();

        Map<String, Object> errors = new HashMap<>();

        // Creating a user
        User user = new User();
        user.setUserName((String) signupPost.get("userName"));
        user.setPassword((String) signupPost.get("password"));
        user.setEmail((String) signupPost.get("email"));
        user.setFirstName((String) signupPost.get("firstName"));
        user.setLastName((String) signupPost.get("lastName"));

        //Validating the user
        UserValidator.validateUser(user, errors);

        // Checking if the user exists in database
        User existantUser = userDAO.getUserByUserName((String) signupPost.get("userName"));

        if (existantUser != null) {
            //If exists
            Error error = new Error("Le nom d'utilisateur existe deja");
            errors.put("userName", error);
        }

        //Checking if password and confirmed Password are equals
        if (!signupPost.get("password").equals(signupPost.get("confirmedPassword"))) {
            Error error = new Error("Les mots de passe ne sont pas identiques");
            errors.put("confirmedPassword", error);
        }

        if (errors.isEmpty()) {
            // There is no Errors
            // Add the user:
            userDAO.addUser(user);
            // Return the created User:
            response.sendJsonObject(user.getUserName());
        }
        else {
            response.sendJsonMapError(errors, 400);
        }
    }

}
