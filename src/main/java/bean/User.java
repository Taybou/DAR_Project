package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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
    @NotNull(message = "Le nom d'utilisateur est obligatoire")
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @SafeHtml(message = "Nom d'utilisateur non valide")
    @Size(min = 2, max = 30, message = "Le nom d'utilisateur doit contenir au moins 2 caractères et au plus 30")
    private String userName;
    @NotNull(message = "Le nom est obligatoire")
    @NotBlank(message = "Le nom est obligatoire")
    @SafeHtml(message = "Nom non valide")
    @Size(min = 2, max = 30, message = "Le nom doit contenir au moins 2 caractères et au plus 30")
    private String lastName;
    @NotNull(message = "Le prenom est obligatoire")
    @NotBlank(message = "Le prenom est obligatoire")
    @SafeHtml(message = "Prenom non valide")
    @Size(min = 2, max = 30, message = "Le prenom doit contenir au moins 2 caractères et au plus 30")
    private String firstName;
    @NotNull(message = "L'email est obligatoire")
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email non valide")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;
    @Size(min = 6, message = "Le mot de passe doit contenir plus de 6 caractères")
    @NotNull(message = "Le mot de passe est obligatoire")
    @NotBlank(message = "Le mot de passe est obligatoire")
    @SafeHtml(message = "Mot de passe non valide")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Pattern(regexp = "Male|Female", message = "Valeurs acceptées : \"Femme\", \"Homme\".")
    @SafeHtml(message = "Sexe non valide")
    private String gender;
    @SafeHtml(message = "Adresse non valide")
    @Size(max = 255)
    private String address;
    @URL(message = "Lien non valide")
    @SafeHtml(message = "Lien non valide")
    private String profilePictureUrl;

    @Size(max = 512)
    @SafeHtml(message = "Description non valide")
    private String description;

    private List<String> booksIsbnList;

    public User() {
        this.booksIsbnList = new ArrayList<>();
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.description = user.getDescription();
        this.booksIsbnList = user.getBooksIsbnList();
    }

    public User(String userName, String lastName, String firstName, String email, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.gender = null;
        this.address = null;
        this.profilePictureUrl = null;
        this.description = null;
        this.booksIsbnList = new ArrayList<>();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

