package servlets.book;

import dao.DAOFactory;
import dao.book.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 16/10/2016.
 */
@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {

    private static final String VUE_BOOK = "myBook";
    BookDAO bookDAO;


    public void init() {
        bookDAO = DAOFactory.getBookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

}
