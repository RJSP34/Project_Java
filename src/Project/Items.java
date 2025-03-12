package Project;


public class Items  {
  private String Name;
  private int Id;
private  boolean travel_bug;
private TravelBug travelBug;
  public Items(String name, boolean travel_bug, TravelBug travelBug,int id) {
    Name = name;
    this.travel_bug = travel_bug;
    this.travelBug = travelBug;
    this.Id=id;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public boolean isTravel_bug() {
    return travel_bug;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public boolean getTravel_bug() {
    return travel_bug;
  }

  public void setTravel_bug(boolean travel_bug) {
    this.travel_bug = travel_bug;
  }

  public TravelBug getTravelBug() {
    return travelBug;
  }

  public void setTravelBug(TravelBug travelBug) {
    this.travelBug = travelBug;
  }
  public void now() {
    if (isTravel_bug()){
      System.out.println("ID:"+getId());
      travelBug.now();
    }
  }


}