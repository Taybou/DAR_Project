package servlets;


import bean.User;
import dao.DAOFactory;
import dao.endroit.EndroitAPIAcess;
import dao.user.UserDAO;
import filters.AuthorizationFilter;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-11-09.
 */



@WebServlet(name = "EndroitServlet")
public class EndroitServlet extends HttpServlet {
    EndroitAPIAcess endroitAPIAcess;
    private UserDAO userDAO;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);
        String address1 = user.getAddress();
        String username = request.getParameter("query");
        String address2 = userDAO.getUserByUserName(username).getAddress();
        System.out.println("Adresse2 --------" + address2);
        System.out.println("Adresse1 --------" + address1);
        ArrayList<bean.googleplaces.Result> results = new ArrayList<bean.googleplaces.Result>();
        if(address1 == null || address2 == null) {
           results = endroitAPIAcess.getNearestPlaces(null, null);
        }else{
            results = endroitAPIAcess.getNearestPlaces(address1, address2);
        }

        jsonResponse.sendJsonObject(results);
    }

    @Override
    public void init() throws ServletException {
         endroitAPIAcess = new EndroitAPIAcess();
        userDAO = DAOFactory.getUserDAO();
    }
}
