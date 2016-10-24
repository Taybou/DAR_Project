
package bean.googlebooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "title",
    "authors",
    "publisher",
    "publishedDate",
    "description",
    "industryIdentifiers",
    "readingModes",
    "pageCount",
    "printedPageCount",
    "dimensions",
    "printType",
    "categories",
    "averageRating",
    "ratingsCount",
    "maturityRating",
    "allowAnonLogging",
    "contentVersion",
    "imageLinks",
    "language",
    "previewLink",
    "infoLink",
    "canonicalVolumeLink"
})
public class VolumeInfo {

    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<String> authors = new ArrayList<String>();
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("publishedDate")
    private String publishedDate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("industryIdentifiers")
    private List<IndustryIdentifier> industryIdentifiers = new ArrayList<IndustryIdentifier>();
    @JsonProperty("readingModes")
    private ReadingModes readingModes;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("printedPageCount")
    private Integer printedPageCount;
    @JsonProperty("dimensions")
    private Dimensions dimensions;
    @JsonProperty("printType")
    private String printType;
    @JsonProperty("categories")
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("averageRating")
    private Integer averageRating;
    @JsonProperty("ratingsCount")
    private Integer ratingsCount;
    @JsonProperty("maturityRating")
    private String maturityRating;
    @JsonProperty("allowAnonLogging")
    private Boolean allowAnonLogging;
    @JsonProperty("contentVersion")
    private String contentVersion;
    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;
    @JsonProperty("language")
    private String language;
    @JsonProperty("previewLink")
    private String previewLink;
    @JsonProperty("infoLink")
    private String infoLink;
    @JsonProperty("canonicalVolumeLink")
    private String canonicalVolumeLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The authors
     */
    @JsonProperty("authors")
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * 
     * @param authors
     *     The authors
     */
    @JsonProperty("authors")
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The publishedDate
     */
    @JsonProperty("publishedDate")
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * 
     * @param publishedDate
     *     The publishedDate
     */
    @JsonProperty("publishedDate")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The industryIdentifiers
     */
    @JsonProperty("industryIdentifiers")
    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    /**
     * 
     * @param industryIdentifiers
     *     The industryIdentifiers
     */
    @JsonProperty("industryIdentifiers")
    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    /**
     * 
     * @return
     *     The readingModes
     */
    @JsonProperty("readingModes")
    public ReadingModes getReadingModes() {
        return readingModes;
    }

    /**
     * 
     * @param readingModes
     *     The readingModes
     */
    @JsonProperty("readingModes")
    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    /**
     * 
     * @return
     *     The pageCount
     */
    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The pageCount
     */
    @JsonProperty("pageCount")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @return
     *     The printedPageCount
     */
    @JsonProperty("printedPageCount")
    public Integer getPrintedPageCount() {
        return printedPageCount;
    }

    /**
     * 
     * @param printedPageCount
     *     The printedPageCount
     */
    @JsonProperty("printedPageCount")
    public void setPrintedPageCount(Integer printedPageCount) {
        this.printedPageCount = printedPageCount;
    }

    /**
     * 
     * @return
     *     The dimensions
     */
    @JsonProperty("dimensions")
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * 
     * @param dimensions
     *     The dimensions
     */
    @JsonProperty("dimensions")
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * 
     * @return
     *     The printType
     */
    @JsonProperty("printType")
    public String getPrintType() {
        return printType;
    }

    /**
     * 
     * @param printType
     *     The printType
     */
    @JsonProperty("printType")
    public void setPrintType(String printType) {
        this.printType = printType;
    }

    /**
     * 
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The averageRating
     */
    @JsonProperty("averageRating")
    public Integer getAverageRating() {
        return averageRating;
    }

    /**
     * 
     * @param averageRating
     *     The averageRating
     */
    @JsonProperty("averageRating")
    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * 
     * @return
     *     The ratingsCount
     */
    @JsonProperty("ratingsCount")
    public Integer getRatingsCount() {
        return ratingsCount;
    }

    /**
     * 
     * @param ratingsCount
     *     The ratingsCount
     */
    @JsonProperty("ratingsCount")
    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * 
     * @return
     *     The maturityRating
     */
    @JsonProperty("maturityRating")
    public String getMaturityRating() {
        return maturityRating;
    }

    /**
     * 
     * @param maturityRating
     *     The maturityRating
     */
    @JsonProperty("maturityRating")
    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    /**
     * 
     * @return
     *     The allowAnonLogging
     */
    @JsonProperty("allowAnonLogging")
    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    /**
     * 
     * @param allowAnonLogging
     *     The allowAnonLogging
     */
    @JsonProperty("allowAnonLogging")
    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    /**
     * 
     * @return
     *     The contentVersion
     */
    @JsonProperty("contentVersion")
    public String getContentVersion() {
        return contentVersion;
    }

    /**
     * 
     * @param contentVersion
     *     The contentVersion
     */
    @JsonProperty("contentVersion")
    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    /**
     * 
     * @return
     *     The imageLinks
     */
    @JsonProperty("imageLinks")
    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    /**
     * 
     * @param imageLinks
     *     The imageLinks
     */
    @JsonProperty("imageLinks")
    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    /**
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The previewLink
     */
    @JsonProperty("previewLink")
    public String getPreviewLink() {
        return previewLink;
    }

    /**
     * 
     * @param previewLink
     *     The previewLink
     */
    @JsonProperty("previewLink")
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    /**
     * 
     * @return
     *     The infoLink
     */
    @JsonProperty("infoLink")
    public String getInfoLink() {
        return infoLink;
    }

    /**
     * 
     * @param infoLink
     *     The infoLink
     */
    @JsonProperty("infoLink")
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    /**
     * 
     * @return
     *     The canonicalVolumeLink
     */
    @JsonProperty("canonicalVolumeLink")
    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    /**
     * 
     * @param canonicalVolumeLink
     *     The canonicalVolumeLink
     */
    @JsonProperty("canonicalVolumeLink")
    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
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
