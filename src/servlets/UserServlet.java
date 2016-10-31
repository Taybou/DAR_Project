package servlets;

import bean.User;
import dao.user.UserDAO;
import errors.Error;
import filters.AuthorizationFilter;
import servlets.wrappers.HttpServletJsonRequest;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-31.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletJsonRequest req = (HttpServletJsonRequest) request;
        HttpServletJsonResponse resp = (HttpServletJsonResponse) response;

        HttpSession session = request.getSession();

        User user =(User) session.getAttribute(AuthorizationFilter.USER_SESSION);


        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) response);

//        @url(/api/users?userName=something) : Get one user
        if (user!= null) {

            jsonResponse.sendJsonObject(user);

        }
//        @url(/api/users) : Get All users
        else {
            Error error = new Error("The session is expired");
            resp.sendJsonError(error, 400);
        }

        response = jsonResponse;
    }
}
