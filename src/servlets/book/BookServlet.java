package servlets.book;

import bean.Book;
import bean.googlebooks.GoogleBook;
import dao.DAOFactory;
import dao.book.BookAPIAccess;
import dao.book.BookDAO;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //merge(request, response);
        /*HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(response);
        Book book = bookDAO.getBookDetails("0061726834");
        jsonResponse.sendJsonObject(book);
        response = jsonResponse;
*/
        String query = req.getParameter("q");
        String id = req.getParameter("id");
        HttpServletJsonResponse jsonResponse = ((HttpServletJsonResponse) resp);

        BookAPIAccess bookAPIAccess = new BookAPIAccess();
//        @url(/api/books?q=something) : find books
        if (query!= null) {
            List<GoogleBook> books = bookAPIAccess.findBooks(query);
            jsonResponse.sendJsonObject(books);
        }
        else if (id != null) {
            GoogleBook book = bookAPIAccess.getBook(id);
            jsonResponse.sendJsonObject(book);
        }
    }
}
