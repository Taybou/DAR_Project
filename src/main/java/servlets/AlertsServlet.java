package servlets;

import bean.Alert;
import bean.User;
import dao.alert.AlertDAO;
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

/**
 * BooXchange Project
 * Created by Al on 07-Nov-16.
 */
public class AlertsServlet extends HttpServlet {

    private AlertDAO alertDAO;

    @Override
    public void init() throws ServletException {
        alertDAO = new AlertDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        response.sendJsonObject(alertDAO.getUserAlerts(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);

        String action = request.getParameter("action");
        String bookISBN = request.getParameter("bookISBN");

        switch (action) {
            case "createAlert":
                if (bookISBN != null) {
                    Alert alert = new Alert(user, bookISBN);
                    alertDAO.addAlert(alert);
                    response.sendJsonObject(alert);
                }
                else {
                    response.sendJsonError(new Error("Veuillez spécifier l'ISBN"), 400);
                }
                break;
            default:
                response.sendJsonError(new Error("Requete non valide"), 400);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletJsonRequest request = (HttpServletJsonRequest) req;
        HttpServletJsonResponse response = (HttpServletJsonResponse) resp;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(AuthorizationFilter.USER_SESSION);
        String bookISBN = request.getParameter("bookISBN");

        if (bookISBN != null) {
            alertDAO.deleteAlert(user, bookISBN);
            response.sendJsonObject("Alert supprimée");
        }
        else {
            response.sendJsonError(new Error("Veuillez spécifier l'ISBN"), 400);
        }
    }
}
