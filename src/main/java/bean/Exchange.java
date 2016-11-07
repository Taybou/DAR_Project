package bean;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 04/11/2016.
 */
@Entity("exchanges")
public class Exchange {

    @Id
    private String _id;

    // Demandeur de l'echange
    private String user1;
    // utilisateur demandé
    private String user2;
    private String isbnBookUser1;
    private String isbnBookUser2;
    private boolean isAccepted;

    public Exchange() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getIsbnBookUser1() {
        return isbnBookUser1;
    }

    public void setIsbnBookUser1(String isbnBookUser1) {
        this.isbnBookUser1 = isbnBookUser1;
    }

    public String getIsbnBookUser2() {
        return isbnBookUser2;
    }

    public void setIsbnBookUser2(String isbnBookUser2) {
        this.isbnBookUser2 = isbnBookUser2;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
