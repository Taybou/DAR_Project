package servlets.wrappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import errors.Error;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

public class HttpServletJsonResponse extends HttpServletResponseWrapper {


    public HttpServletJsonResponse(HttpServletResponse response) {
        super(response);
        this.setContentType("application/json");
    }

    /*
    * Transform an object to Json and send it.
    * */
    public void sendJsonObject(Object o) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(this.getWriter(), o);
    }

    /*
    * Transform a Map<String, Object> map to Json and send it.
    * */
    public void sendJsonMap(Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(this.getWriter(), map);
    }

    public void sendJsonError(Error error, int statusCode) throws IOException {
        this.setStatus(statusCode);
        this.sendJsonObject(error);
    }

    public void sendJsonMapError(Map<String, Object> errors, int statusCode) throws IOException {
        this.setStatus(statusCode);
        this.sendJsonMap(errors);
    }
}
