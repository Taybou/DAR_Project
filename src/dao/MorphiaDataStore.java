/*
  BooXchange Project
  Created by Al on 06-Oct-16.
 */

package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
* MorphiaDataStore is a package private Class, it is accessible only by classes in the same package and it cannot be extended
* */

final public class MorphiaDataStore {


    private static final String DATABASE_SERVER_PRODUCTION = "mongodb://booxchange:booxchange@ds048319.mlab.com:48319/booxchange";
    private static final String DATABASE_SERVER_DEVELOPMENT = "mongodb://localhost:27017/";
    private static final String DATABASE_NAME = "booxchange";

    // datastoreInstance and mongoClient are Singletons
    /*
    * See Morphia Doc and MongoDB Doc for more information avout Datastore and MongoClient
    *
    */
    private static Datastore datastore = null;

    /*
    * This Class is a Utility class and it should not be instantiated
    * */
    private MorphiaDataStore() {

    }

    public static Datastore getDataStore() {

        if (datastore != null) return datastore;
        else {
//            Creating a morphia instance
            Morphia morphia = new Morphia();
//            Mapping the beans
            morphia.mapPackage("bean");
//            Creating a MongoDB instance

            String environment;

            MongoClient mongoClient;
            try {
                InitialContext initialContext = new InitialContext();
                environment = (String) initialContext.lookup("java:comp/env/environment");

                if (environment.compareTo("production") == 0) {
                    System.out.println("MongoDB Production mode");
                    mongoClient = new MongoClient(new MongoClientURI(DATABASE_SERVER_PRODUCTION));
                } else {
                    System.out.println("MongoDB Development mode");
                    mongoClient = new MongoClient(new MongoClientURI(DATABASE_SERVER_DEVELOPMENT));
                }
            } catch (NamingException e) {
                System.out.println("MongoDB Development mode: No environment variable found");
                mongoClient = new MongoClient(new MongoClientURI(DATABASE_SERVER_DEVELOPMENT));
            }

            datastore = morphia.createDatastore(mongoClient, DATABASE_NAME);
            datastore.ensureIndexes();
            return datastore;
        }
    }
}
