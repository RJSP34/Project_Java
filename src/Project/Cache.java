package Project;

import java.util.ArrayList;


public class Cache {
  private String Type;
  private ArrayList<Items> Objects;
  private String Dificulty;
private Cache_log d;
  private GPS gps;
  private int ID_CACHE;

  public Cache(String type, ArrayList<Items> objects, String dificulty, GPS gps, int ID_CACHE,Cache_log d) {
    Type = type;
    Objects = objects;
    Dificulty = dificulty;
    this.gps = gps;
    this.ID_CACHE = ID_CACHE;
    this.d=d;
  }

  public Cache(String type, GPS gps, int ID_CACHE) {
    Type = type;
    this.gps = gps;
    this.ID_CACHE = ID_CACHE;

  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }
  public void setDificulty( String D) {

    this.Dificulty=D;
  }
  public String getDificulty() {
    return Dificulty;
  }

  public GPS getGps() {
    return gps;
  }

  public void setGps(GPS gps) {
    this.gps = gps;
  }

  public ArrayList<Items> getObjects() {
    return Objects;
  }

  public void setObjects(ArrayList<Items> objects) {
    Objects = objects;
  }



  public Cache_log getD() {
    return d;
  }

  public void setD(Cache_log d) {
    this.d = d;
  }

  public int getID_CACHE() {
    return ID_CACHE;
  }

  public void setID_CACHE(int ID_CACHE) {
    this.ID_CACHE = ID_CACHE;
  }

  public boolean isEmpty() {
    return this.Objects == null;
  }



  public void isString() {
    System.out.println(this.ID_CACHE);
    System.out.println(this.Type);
    System.out.println(this.Objects);
    System.out.println(this.Dificulty);
    System.out.println(this.gps);

  }

  public Items removeitemsfromcache(Items I) {
    if(!this.Objects.remove(I)){
      System.out.println("Item:"+I.getName()+"Does not exist");
      return null;
    }
  this.Objects.remove(I);
    return I;
  }

  public void additemstoCache(Items I) {
    this.Objects.add(I);
  }
public void Visited_log_cache(int id){
  if (this.d==null) {
    this.d = new Cache_log(new ArrayList<>());
  }
this.d.insertLog(String.valueOf(id),"Visited",String.valueOf(this.ID_CACHE));
  }

  public double distanceCache(Cache G) {
    return Math.sqrt(Math.pow(Math.abs(gps.getLatitude()-G.getGps().getLatitude()),2)+Math.pow(Math.abs(gps.getLongitude()-G.getGps().getLongitude()),2));
  }
  public Items Items_get_array(Integer a) {
    for (Items object : this.Objects) {
      if (a == object.getId()) {
        return object;
      }

    }
    return null;
  }
  public Items removeItems(Items I) {
    if (!this.isItems(I.getId())) {
      System.out.println("Item:" + I.getName() + "Does not exist");
      return null;
    }
    this.Objects.remove(I);
    return I;

  }
  public boolean isItems(int i) {
    for (Items object : this.Objects) {
      if (object.getId() == i) {
        return true;

      }

    }
    return false;
  }
}