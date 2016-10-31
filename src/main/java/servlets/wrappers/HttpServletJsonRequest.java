package servlets.wrappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Map;


/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

public class HttpServletJsonRequest extends HttpServletRequestWrapper {
    private ByteArrayOutputStream cachedBytes;
    private Object jsonObject;
    private Map<String, Object> jsonMap;

    public HttpServletJsonRequest(HttpServletRequest request) {
        super(request);
        this.jsonObject = null;
        this.jsonMap = null;
    }

    /*
    * Create a T instance by parsing the body of the json request
    * */
    @SuppressWarnings("unchecked")
    public <T> T getJsonObject(Class<T> aClass) throws IOException {
        if (jsonObject != null) {
            return (T) jsonObject;
        }
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            T val = objectMapper.readValue(this.getReader(), aClass);
            jsonObject = val;
            return  val;
        }
    }

    /*
    * Create a Map<String, Object> instance by parsing the body of the json request
    * */
    public Map<String, Object> getJsonMap() throws IOException {

        if (jsonMap != null) {
            return jsonMap;
        }
        else {
            Map<String, Object> objectMap;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMap = objectMapper.readValue(this.getReader(), new TypeReference<Map<String, Object>>() {});
            return objectMap;
        }
    }


    /*
    * The rest of the code is making sur that the request body stream can be read multiple times by caching the buffer
    * See this post for more information :
    * http://stackoverflow.com/questions/10210645
    * */
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes == null)
            cacheInputStream();

        return new CachedServletInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private void cacheInputStream() throws IOException {
    /* Cache the inputstream in order to read it multiple times. For
     * convenience, I use apache.commons IOUtils
     */
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    private class CachedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream input;

        /*Package private */
        CachedServletInputStream() {
      /* create a new input stream from the cached request body */
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return input.available() == 0;
        }

        @Override
        public boolean isReady() {
            return input!=null;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public int read() throws IOException {
            return input.read();
        }
    }
}
