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

        String httpMethod = request.getMethod();
        if (httpMethod.compareToIgnoreCase("post") == 0 || httpMethod.compareToIgnoreCase("put") == 0 || httpMethod.compareToIgnoreCase("delete") == 0) {

            String contentType = request.getContentType();
            if (contentType == null || contentType.compareToIgnoreCase("application/json") != 0) {
                Error error = new Error("Content Type must be \"application/json\"");
                HttpServletJsonResponse httpServletJsonResponse = new HttpServletJsonResponse((HttpServletResponse) servletResponse);
                httpServletJsonResponse.setStatus(400);
                httpServletJsonResponse.sendJsonObject(error);
            }
            else {
                this.forwardRequest(servletRequest, servletResponse, filterChain);
            }
        }
        else {
            this.forwardRequest(servletRequest, servletResponse, filterChain);
        }
    }

    private void forwardRequest(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletJsonRequest httpServletJsonRequest = new HttpServletJsonRequest((HttpServletRequest) servletRequest);
        HttpServletJsonResponse httpServletJsonResponse = new HttpServletJsonResponse((HttpServletResponse) servletResponse);

        filterChain.doFilter(httpServletJsonRequest, httpServletJsonResponse);
    }

    @Override
    public void destroy() {

    }
}
