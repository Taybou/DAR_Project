package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * BooXchange Project
 * Created by Al on 14-Oct-16.
 */
public class AuthorizationFilter implements Filter {

    public static final String USER_SESSION = "userSession";
    private static final String SIGNIN_PAGE = "/signin";
    private static final String SIGNUP_PAGE = "/signup";

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
            // Redirect to connexion page;
            response.sendRedirect(SIGNIN_PAGE);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
