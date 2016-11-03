package servlets.book;

import bean.User;
import bean.googlebooks.GoogleBook;
import dao.DAOFactory;
import dao.book.BookDAO;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        //String id = request.getParameter("id");

        switch (action) {
            case "search":
                if (query != null) {
                    List<GoogleBook> books = bookDAO.findBooks(query);
                    jsonResponse.sendJsonObject(books);
                }
                break;
            //TODO: This action should be moved to the user API. it concerns the ressource User and not Book
            case "add":
                //TODO: No Need to specify the userName, you can get it by using the session (See authorisation Filter)
                String userName = request.getParameter("userName");
                List booksIsbnList = new ArrayList();
                booksIsbnList.add(query);
                User user = new User(userName, booksIsbnList);

                bookDAO.addBooks(user);

                jsonResponse.sendJsonObject("successfully added");
                break;

            case "details":
                if (query != null) {
                    GoogleBook book = bookDAO.getBookDetails(query);
                    jsonResponse.sendJsonObject(book);
                }
                break;
        }

//        if (id != null) {
//            GoogleBook book = bookAPIAccess.getBook(id);
//            jsonResponse.sendJsonObject(book);
//        }
    }
}
