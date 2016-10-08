/*
  BooXchange Project
  Created by Al on 06-Oct-16.
 */

package dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/*
* MorphiaDataStore is a package private Class, it is accessible only by classes in the same package and it cannot be extended
* */

final class MorphiaDataStore {

    // datastoreInstance and mongoClient are Singletons
    /*
    * See Morphia Doc and MongoDB Doc for more information avout Datastore and MongoClient
    * */
    private static Datastore datastoreInstance = null;
    private static MongoClient mongoClient = null;
    private static Morphia morphia = null;


    private static final String DATABASE_SERVER = "localhost";
    private static final int DATABASE_SERVER_PORT = 27017;

    private static final String DATABASE_NAME = "booxchange";

    /*
    * This Class is a Utility class and it should not be instantiated
    * */
    private MorphiaDataStore() {

    }


    static Datastore getDataStore() {

        if (datastoreInstance != null) return datastoreInstance;
        else {
//            Creating a morphia instance
            morphia = new Morphia();
//            Mapping the beans
            morphia.mapPackage("beans");
//            Creating a MongoDB instance
            mongoClient = new MongoClient(DATABASE_SERVER, DATABASE_SERVER_PORT);
            datastoreInstance = morphia.createDatastore(mongoClient, DATABASE_NAME);
            datastoreInstance.ensureIndexes();
            return datastoreInstance;
        }
    }

    static MongoClient getMongoClient() {
        return mongoClient;
    }

    static Morphia getMorphia() {
        return morphia;
    }
}
