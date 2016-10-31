
package bean.googlebooks;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "kind",
        "totalItems",
        "items"
})
public class BookSearchResult {

    @JsonProperty("kind")
    private String kind;
    @JsonProperty("totalItems")
    private Integer totalItems;
    @JsonProperty("items")
    private List<GoogleBook> items = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The kind
     */
    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The totalItems
     */
    @JsonProperty("totalItems")
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * @param totalItems The totalItems
     */
    @JsonProperty("totalItems")
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    /**
     * @return The items
     */
    @JsonProperty("items")
    public List<GoogleBook> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    @JsonProperty("items")
    public void setItems(List<GoogleBook> items) {
        this.items = items;
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
