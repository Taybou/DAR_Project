package servlets;

import bean.Exchange;
import dao.DAOFactory;
import dao.exchange.ExchangeDAO;
import servlets.wrappers.HttpServletJsonRequest;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 04/11/2016.
 */
@WebServlet(name = "ExchangeServlet")
public class ExchangeServlet extends HttpServlet {

    ExchangeDAO exchangeDAO;

    public void init() {
        exchangeDAO = DAOFactory.getExchangeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletJsonRequest jsonRequest = ((HttpServletJsonRequest) request);
        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) response);
        // Map<String, Object> exchangePost = jsonRequest.getJsonMap();

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    Exchange exchange = new Exchange();
/*                  exchange.set_id(String.valueOf(System.currentTimeMillis()));
                    exchange.setUser1((String) exchangePost.get("user1"));
                    exchange.setUser2((String) exchangePost.get("user2"));
                    exchange.setAccepted(false);
                    exchange.setIsbnBookUser1((String) exchangePost.get("isbnBookUser1"));
                    exchange.setIsbnBookUser2((String) exchangePost.get("isbnBookUser2"));*/
                    exchange.set_id(String.valueOf(System.currentTimeMillis()));
                    exchange.setUser1(request.getParameter("user1"));
                    exchange.setUser2(request.getParameter("user2"));
                    exchange.setAccepted(false);
                    exchange.setIsbnBookUser1(request.getParameter("isbnBookUser1"));
                    exchange.setIsbnBookUser2(request.getParameter("isbnBookUser2"));

                    exchangeDAO.addExchange(exchange);
                    jsonResponse.sendJsonObject("successfully added");
                    break;

                case "accept":
                    String id = request.getParameter("id");
                    exchangeDAO.acceptExchange(id);
                    jsonResponse.sendJsonObject("successfully accepted");
                    break;

                case "viewSubmitted":
                    String user = request.getParameter("user1");
                    List<Exchange> list = exchangeDAO.getExchangeSubmitted(user);
                    jsonResponse.sendJsonObject(list);
                    break;

                case "viewNotified":
                    user = request.getParameter("user2");
                    list = exchangeDAO.getExchangeNotified(user);
                    jsonResponse.sendJsonObject(list);
                    break;
            }
        }
    }
}
