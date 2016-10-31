
package bean.googlebooks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "height",
    "width",
    "thickness"
})
public class Dimensions {

    @JsonProperty("height")
    private String height;
    @JsonProperty("width")
    private String width;
    @JsonProperty("thickness")
    private String thickness;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The height
     */
    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The width
     */
    @JsonProperty("width")
    public String getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    @JsonProperty("width")
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The thickness
     */
    @JsonProperty("thickness")
    public String getThickness() {
        return thickness;
    }

    /**
     * 
     * @param thickness
     *     The thickness
     */
    @JsonProperty("thickness")
    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
