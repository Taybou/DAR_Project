package dao.book;

import bean.googlebooks.GoogleBook;
import bean.googlebooks.IndustryIdentifier;
import bean.googlebooks.VolumeInfo;
import dao.MorphiaDataStore;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.List;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-10-15.
 */
public class BookDAOImpl implements BookDAO {

    private Datastore datastore;
    private BookAPIAccess bookAPIAccess;

    public BookDAOImpl() {
        datastore = MorphiaDataStore.getDataStore();
        bookAPIAccess = new BookAPIAccess();
    }

    @Override
    public GoogleBook getBookDetails(String isbn) {
        GoogleBook book = bookAPIAccess.getBook(isbn);
        return book;
    }

    @Override
    public List<GoogleBook> findBooks(String query) {

        List<GoogleBook> books = bookAPIAccess.findBooks(query);
        List<GoogleBook> booksList = new ArrayList<>();

        for (GoogleBook book : books) {
            GoogleBook mBook = new GoogleBook();
            VolumeInfo volumeInfo = new VolumeInfo();

            volumeInfo.setTitle(book.getVolumeInfo().getTitle());
            volumeInfo.setAuthors(book.getVolumeInfo().getAuthors());
            volumeInfo.setImageLinks(book.getVolumeInfo().getImageLinks());
            boolean isValid = false;

            for (IndustryIdentifier industryIdentifier :
                    book.getVolumeInfo().getIndustryIdentifiers()) {
                if (industryIdentifier.getType().equals("ISBN_13")) {
                    List<IndustryIdentifier> list = new ArrayList<>();
                    list.add(industryIdentifier);
                    volumeInfo.setIndustryIdentifiers(list);
                    isValid = true;
                }
            }

            if (isValid) {
                mBook.setVolumeInfo(volumeInfo);
                booksList.add(mBook);
            }
        }

        return booksList;
    }

}
