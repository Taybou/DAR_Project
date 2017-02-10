package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * BooXchange Project
 * Created by Al on 07/02/2017.
 */
public class TrackingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Tracking user*/
        String userId;
        if (req.getHeader("If-Modified-Since") == null
                || req.getHeader("If-Modified-Since").compareTo("") == 0 ) {
            HttpSession session = req.getSession();
            resp.setHeader("Last-Modified", session.getId());
            userId = session.getId();
        }
        else {
            userId = req.getHeader("If-Modified-Since");
            resp.setHeader("Last-Modified", userId);
        }

        System.out.println(userId);

        req.setAttribute("userId", userId);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/advert.jsp" ).forward(req, resp);

        /*Cookie trackCookie = new Cookie("BOOX-TRACK", session.getId());
        trackCookie.setPath("/");
        resp.addCookie(trackCookie);*/


        /* ******************** */
    }
}
