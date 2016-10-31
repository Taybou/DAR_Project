
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
    "country",
    "viewability",
    "embeddable",
    "publicDomain",
    "textToSpeechPermission",
    "epub",
    "pdf",
    "webReaderLink",
    "accessViewStatus",
    "quoteSharingAllowed"
})
public class AccessInfo {

    @JsonProperty("country")
    private String country;
    @JsonProperty("viewability")
    private String viewability;
    @JsonProperty("embeddable")
    private Boolean embeddable;
    @JsonProperty("publicDomain")
    private Boolean publicDomain;
    @JsonProperty("textToSpeechPermission")
    private String textToSpeechPermission;
    @JsonProperty("epub")
    private Epub epub;
    @JsonProperty("pdf")
    private Pdf pdf;
    @JsonProperty("webReaderLink")
    private String webReaderLink;
    @JsonProperty("accessViewStatus")
    private String accessViewStatus;
    @JsonProperty("quoteSharingAllowed")
    private Boolean quoteSharingAllowed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The viewability
     */
    @JsonProperty("viewability")
    public String getViewability() {
        return viewability;
    }

    /**
     * 
     * @param viewability
     *     The viewability
     */
    @JsonProperty("viewability")
    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    /**
     * 
     * @return
     *     The embeddable
     */
    @JsonProperty("embeddable")
    public Boolean getEmbeddable() {
        return embeddable;
    }

    /**
     * 
     * @param embeddable
     *     The embeddable
     */
    @JsonProperty("embeddable")
    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    /**
     * 
     * @return
     *     The publicDomain
     */
    @JsonProperty("publicDomain")
    public Boolean getPublicDomain() {
        return publicDomain;
    }

    /**
     * 
     * @param publicDomain
     *     The publicDomain
     */
    @JsonProperty("publicDomain")
    public void setPublicDomain(Boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    /**
     * 
     * @return
     *     The textToSpeechPermission
     */
    @JsonProperty("textToSpeechPermission")
    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    /**
     * 
     * @param textToSpeechPermission
     *     The textToSpeechPermission
     */
    @JsonProperty("textToSpeechPermission")
    public void setTextToSpeechPermission(String textToSpeechPermission) {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    /**
     * 
     * @return
     *     The epub
     */
    @JsonProperty("epub")
    public Epub getEpub() {
        return epub;
    }

    /**
     * 
     * @param epub
     *     The epub
     */
    @JsonProperty("epub")
    public void setEpub(Epub epub) {
        this.epub = epub;
    }

    /**
     * 
     * @return
     *     The pdf
     */
    @JsonProperty("pdf")
    public Pdf getPdf() {
        return pdf;
    }

    /**
     * 
     * @param pdf
     *     The pdf
     */
    @JsonProperty("pdf")
    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    /**
     * 
     * @return
     *     The webReaderLink
     */
    @JsonProperty("webReaderLink")
    public String getWebReaderLink() {
        return webReaderLink;
    }

    /**
     * 
     * @param webReaderLink
     *     The webReaderLink
     */
    @JsonProperty("webReaderLink")
    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    /**
     * 
     * @return
     *     The accessViewStatus
     */
    @JsonProperty("accessViewStatus")
    public String getAccessViewStatus() {
        return accessViewStatus;
    }

    /**
     * 
     * @param accessViewStatus
     *     The accessViewStatus
     */
    @JsonProperty("accessViewStatus")
    public void setAccessViewStatus(String accessViewStatus) {
        this.accessViewStatus = accessViewStatus;
    }

    /**
     * 
     * @return
     *     The quoteSharingAllowed
     */
    @JsonProperty("quoteSharingAllowed")
    public Boolean getQuoteSharingAllowed() {
        return quoteSharingAllowed;
    }

    /**
     * 
     * @param quoteSharingAllowed
     *     The quoteSharingAllowed
     */
    @JsonProperty("quoteSharingAllowed")
    public void setQuoteSharingAllowed(Boolean quoteSharingAllowed) {
        this.quoteSharingAllowed = quoteSharingAllowed;
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
