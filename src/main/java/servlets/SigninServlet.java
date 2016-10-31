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
 * Created by Al on 16-Oct-16.
 */
public class SigninServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;
        HttpSession session = request.getSession();

        Map<String, Object> signinPost = request.getJsonMap();

        UserDAO userDAO = DAOFactory.getUserDAO();
        //Checking if user exists
        User user = userDAO.getUserByUserName((String) signinPost.get("userName"));

//        Check if user exists
//        Check if password is correct
        if (user != null && user.getPassword().equals(signinPost.get("password"))) {
            session.setAttribute(AuthorizationFilter.USER_SESSION, user);
            response.sendRedirect("/");
        }
//        Error, user does not exist or incorrect password
        else {
            session.setAttribute(AuthorizationFilter.USER_SESSION, null);
            Error error = new Error("Nom d'utilisateur ou Mot de passe incorrect");
            response.sendJsonError(error, 400);
        }
    }
}
