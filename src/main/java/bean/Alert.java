package bean;

import org.hibernate.validator.constraints.SafeHtml;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * BooXchange Project
 * Created by Al on 07-Nov-16.
 */
public class Alert {

    @Id
    private String alertId;

    @Reference
    private User user;
    @SafeHtml(message = "ISBN non valide")
    private String bookISBN;

    public Alert() {
    }

    public Alert(User user, String bookISBN) {
        this.user = user;
        this.bookISBN = bookISBN;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}
