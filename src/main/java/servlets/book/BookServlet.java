package servlets.book;

import bean.googlebooks.GoogleBook;
import dao.DAOFactory;
import dao.book.BookDAO;
import errors.Error;
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
 * Created by Mohamed Tayeb on 16/10/2016.
 */
@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {
    BookDAO bookDAO;

    public void init() {
        bookDAO = DAOFactory.getBookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) response);

        String query = request.getParameter("query");
        String action = request.getParameter("action");

        switch (action) {
            case "search":
                if (query != null) {
                    List<GoogleBook> books = bookDAO.findBooks(query);
                    jsonResponse.sendJsonObject(books);
                }
                break;
            case "details":
                if (query != null) {
                    GoogleBook book = bookDAO.getBookDetails(query);
                    jsonResponse.sendJsonObject(book);
                }
                break;
            case "searchOwned":
                if (query != null) {
                    List<GoogleBook> books = bookDAO.findOwnedBooks(query);
                    jsonResponse.sendJsonObject(books);
                }
                break;
            default:
                jsonResponse.sendJsonError(new Error("Requete Invalide"), 400);
                break;
        }

    }
}
