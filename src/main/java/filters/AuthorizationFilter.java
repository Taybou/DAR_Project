package filters;

import errors.Error;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
public class AuthorizationFilter implements Filter {

    public static final String USER_SESSION = "userSession";
    public static final String SIGNIN_PAGE = "/signin";
    public static final String SIGNUP_PAGE = "/signup";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Getting the session;
        HttpSession session = request.getSession();

        // Checking if the user exists in the session;
        if (session.getAttribute(USER_SESSION) == null) {
            // Redirect to connexion page or send unauthorized;
            if (request.getRequestURI().matches("/api/(.*)")) {
                HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(response);
                jsonResponse.sendJsonError(new Error("Unauthorized, please signin"), 401);
            }
            else {
                response.sendRedirect(SIGNIN_PAGE);
            }
        }
        else {

            if (request.getRequestURI().matches("/api/(.*)")) {

                String XSRFHeader = request.getHeader("X-XSRF-TOKEN");
                System.out.println(XSRFHeader);

                Cookie[] cookies = request.getCookies();

                int i = 0 ;
                Cookie cookie = null;
                while (i < cookies.length) {
                    System.out.println(cookies[i].getName());
                    if (cookies[i].getName().equals("XSRF-TOKEN")) {
                        cookie = cookies[i];
                        break;
                    }
                    i++;
                }
                System.out.println(cookies.length);
                System.out.println(cookie);

                if (cookie != null && XSRFHeader != null && cookie.getValue().equals(XSRFHeader)) {
                    filterChain.doFilter(request, response);
                }
                else {
                    HttpServletJsonResponse jsonResponse = new HttpServletJsonResponse(response);
                    jsonResponse.sendJsonError(new Error("Unauthorized, XSRF/CSRF is not allowed"), 401);
                }
            }
            else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
