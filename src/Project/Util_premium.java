package Project;

import java.util.ArrayList;

public class Util_premium extends Utilizador {

  private float Saldo;
private ArrayList<Integer> Owned;
  public Util_premium(String name, int numbVisted, ArrayList<Items> objects, int ID_UTI, float saldo,Util_log log,ArrayList<Integer> visited_cache,GPS loc,ArrayList<Integer> owned) {
    super(name, numbVisted, objects, ID_UTI,log,visited_cache,loc);
    Saldo = saldo;
    Owned=owned;
  }

  public float getSaldo() {
    return Saldo;
  }

  public void setSaldo(float saldo) {
    Saldo = saldo;
  }

  public ArrayList<Integer> getOwned() {
    return Owned;
  }

  public void setOwned(ArrayList<Integer> owned) {
    Owned = owned;
  }

  public void setBalence(float f) {
    try{
      this.Saldo=f;
    }catch (IllegalArgumentException as){
      System.out.println("Erro"+as);
    }
  }

  public void Wildraw(float f) {
    if(this.Saldo>=f){
      this.Saldo-=f;
      return;
    }
    System.out.println("Not enoght money");
  }

  public void addmoney(float f) {
    if(f>=0.0f){
      this.Saldo+=f;
      return;
    }
    System.out.println("Not valied");
  }

}