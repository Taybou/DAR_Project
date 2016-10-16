package servlets.book;

import dao.DAOFactory;
import dao.book.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        merge(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        merge(request, response);
    }

    private void merge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Ceci est une page générée depuis une servlet.</p>");
        out.println("</body>");
        out.println("</html>");
        try {
            System.out.println(bookDAO.getBookDetails("0441172717"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
