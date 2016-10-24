package servlets.book;

import bean.User;
import dao.DAOFactory;
import dao.book.BookDAO;

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
 * Created by Mohamed Tayeb on 20/10/2016.
 */
@WebServlet(name = "BookSearchServlet")
public class BookSearchServlet extends HttpServlet {

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

        bookDAO.findBooksByTitle_Author_ISBN("0441172717");

        List booksIsbnList = new ArrayList();
        booksIsbnList.add("784");
        booksIsbnList.add("555");

        User user = new User("b", booksIsbnList);
        bookDAO.addBooks(user);
    }
}
