package Project;
import java.io.Serializable;
public class Geo_Cache extends  Cache implements Serializable{
    public Geo_Cache(String type, GPS gps, int ID_CACHE) {

        super(type, gps, ID_CACHE);
    }
    
}
