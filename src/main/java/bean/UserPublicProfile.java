package bean;
import java.util.List;

/**
 * BooXchange Project
 * Created by Al on 04-Nov-16.
 */
public class UserPublicProfile {

    private String userName;
    private String lastName;
    private String firstName;
    private String gender;
    private String address;
    private String profilePictureUrl;

    private List<String> booksIsbnList;
    private String description;

    public UserPublicProfile(User user) {

        this.userName = user.getUserName();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.description = user.getDescription();
        this.booksIsbnList = user.getBooksIsbnList();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<String> getBooksIsbnList() {
        return booksIsbnList;
    }

    public void setBooksIsbnList(List<String> booksIsbnList) {
        this.booksIsbnList = booksIsbnList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static UserPublicProfile getUserProfile(User user) {
        if (user != null) {
            return new UserPublicProfile(user);
        }
        else return null;
    }
}
