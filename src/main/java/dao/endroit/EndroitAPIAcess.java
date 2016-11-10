package dao.endroit;

import bean.googleendroit.GeoEndroit;
import bean.googleendroit.Geometry;
import bean.googleendroit.Location;
import bean.googleendroit.Result;
import bean.googleplaces.Places;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * BooXchange Project
 * Created by Nour Elislam on 2016-11-09.
 */
public class EndroitAPIAcess {

    public static String GOOGLE_MAPS_URI = "https://maps.googleapis.com/maps/api/geocode/json";
    public static String GOOGLE_MAPS_KET = "AIzaSyBgKQMp-a7NqPJkXH7-m4UuSMbxwEzleRU";
    public static String GOOGLE_MAPS_PLACES_URI ="https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    public EndroitAPIAcess() {

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public Location getLatLongByAddress(String address) {
        System.out.println(address);
        if (!address.equals("")) {
            try {
                HttpResponse<GeoEndroit> response = Unirest.get("https://maps.googleapis.com/maps/api/geocode/json")
                        .header("cache-control", "no-cache")
                        .queryString("address", address)
                        .queryString("key", GOOGLE_MAPS_KET)
                        .asObject(GeoEndroit.class);
                GeoEndroit result = response.getBody();
                if(result == null){
                    System.out.println("Latituuuuuuuuuuude");
                    return null;
                }else{
                    System.out.println("longitudeeeeeee" +result.getResults().size());

                    return result.getResults().get(0).getGeometry().getLocation();
                }

            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
            return null;
        }

    public Location getMiddleAddress(Location address1 , Location address2 ){


            Location location = new Location();
            location.setLat((address1.getLat() + address2.getLat()) / 2);
            location.setLng((address1.getLng() + address2.getLng()) / 2);
            return location;

    }

    public ArrayList<bean.googleplaces.Result> getNearestPlaces (String address1 , String address2){
        String location = "";
        if(address1==null || address2==null){
            location = "48.864049,2.331053";
        }else {
            Location loc1 = getLatLongByAddress(address2);
            Location loc2 = getLatLongByAddress(address2);


            Location loc3 = getMiddleAddress(loc1, loc2);
            location = (loc3.getLat().toString() + "," + loc3.getLng().toString());

        }
        System.out.println(location);

        try {
            HttpResponse<Places> response = Unirest.get("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius=500&types=book_store&key=AIzaSyBgKQMp-a7NqPJkXH7-m4UuSMbxwEzleRU")
                    .header("cache-control", "no-cache")
                 //   .queryString("location", location)
                 //   .queryString("radius",500)
                 //   .queryString("types","library|book_store")
                 //   .queryString("key", GOOGLE_MAPS_KET)
                    .asObject(Places.class);
            Places result = response.getBody();

            ArrayList<bean.googleplaces.Result> results = new ArrayList<bean.googleplaces.Result>();
            System.out.println("SIZE--------------"+result.getResults().size());
            for(int i=0 ; i<result.getResults().size();i++){
                results.add(result.getResults().get(i));
            }
         //   System.out.println("SIZE--------------"+result.getResults().get(0).);
            return results; // ( ArrayList<bean.googleplaces.Geometry>) result.getResults().size();
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        return null ;
    }


}
