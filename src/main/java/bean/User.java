package bean;

import org.mongodb.morphia.annotations.*;

import java.util.List;

/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

/*
* This Class is mapped to a document in MongoDB
* */

@Entity("users")
public class User {

    /*
    * The userName is considered as a unique identifier for users
    * */
    @Id
    private String userName;

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Gender gender;
    private String address;
    private String profilePictureUrl;

    private List<String> booksIsbnList;

    public User() {
    }

    public User(String userName, String lastName, String firstName, String email, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, List<String> booksIsbnList) {
        this.userName = userName;
        this.booksIsbnList = booksIsbnList;
    }

    public List<String> getBooksIsbnList() {
        return booksIsbnList;
    }

    public void setBooksIsbnList(List<String> booksIsbnList) {
        this.booksIsbnList = booksIsbnList;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

