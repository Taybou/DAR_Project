package filters;

import errors.Error;
import servlets.wrappers.HttpServletJsonRequest;
import servlets.wrappers.HttpServletJsonResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

/*
*
* It verifies it the content type of the HTTP request is JSON
* If it is not, the request is rejected
* It it is, the request and response are Wrapped in HttpServletJsonRequest and HttpServletJsonResponse
* */

public class JsonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getContentType().compareToIgnoreCase("application/json") == 0) {
            HttpServletJsonRequest httpServletJsonRequest = new HttpServletJsonRequest(request);
            HttpServletJsonResponse httpServletJsonResponse = new HttpServletJsonResponse((HttpServletResponse) servletResponse);

            filterChain.doFilter(httpServletJsonRequest, httpServletJsonResponse);
        }
        else {
            Error error = new Error("Content Type must be \"application/json\"");
            HttpServletJsonResponse httpServletJsonResponse = new HttpServletJsonResponse((HttpServletResponse) servletResponse);
            httpServletJsonResponse.setStatus(400);
            httpServletJsonResponse.sendJsonObject(error);
        }
    }

    @Override
    public void destroy() {

    }
}
