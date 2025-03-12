package Project;

public class GPS {

  private double Latitude;

  private double Longitude;

  private  String Regiao;

  public GPS(double latitude, double longitude, String regiao) {
    Latitude = latitude;
    Longitude = longitude;
    Regiao = regiao;
  }

  public double getLatitude() {
    return Latitude;
  }

  public void setLatitude(double latitude) {
    Latitude = latitude;
  }

  public double getLongitude() {
    return Longitude;
  }

  public void setLongitude(double longitude) {
    Longitude = longitude;
  }

  public String getRegiao() {
    return Regiao;
  }

  public void setRegiao(String regiao) {
    Regiao = regiao;
  }
  public int compare_GPS(GPS g) {
    if (this.Latitude==g.Latitude && this.Longitude==g.Longitude && this.Regiao.compareTo(g.Regiao)==0){

      return 0;

    }
     return 1;
  }
}