package Project;

import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;
import Classimports.RedBlackBST_copy;

public class Utilizador {

   private String Name;
  private int NumbVisted;
  private ArrayList<Items> Objects;
  private ArrayList<Integer> Visited_cache;
  private int ID_UTI;
private Util_log log;
private GPS Local;

  public Utilizador(String name, int numbVisted, ArrayList<Items> objects, int ID_UTI,Util_log log,ArrayList<Integer> visited_cache, GPS loc) {
    Name = name;
    NumbVisted = numbVisted;
    Objects = objects;
    this.ID_UTI = ID_UTI;
    this.log=log;
    Visited_cache=visited_cache;
    Local=loc;
  }

  public GPS getLocal() {
    return Local;
  }

  public void setLocal(GPS local) {
    Local = local;
  }

  public boolean isItems(Items I) {
    for (Items object : this.Objects) {
      if (object == I) {
        return true;

      }

    }
    return false;
  }

  public void transferItemCache(Items I, Cache C) {
if(!this.isItems(I) ||C==null){
  System.out.println("NOT EXTIST");
  return;
}
Items k;

if((k=removeItems(I))==null){
  System.out.println("Error");
  return;
}
    if(k.isTravel_bug()){
k.getTravelBug().getHistory_Ches().add(C.getID_CACHE());
k.getTravelBug().getLog_t().insertLog(String.valueOf(k.getId()),"Transferio",String.valueOf(C.getID_CACHE()));
    }
    C.getObjects().add(k);
this.log.insertLog(String.valueOf(this.ID_UTI),"Transferio",String.valueOf(C.getID_CACHE()));
C.getD().insertLog(String.valueOf(this.ID_UTI),"Transferio",String.valueOf(C.getID_CACHE()));
  }
  public void transferItemCache_Utilizador(Items I, Cache C) {
    if(I==null||C==null){
      return;
    }
    if(!C.isItems(I.getId())){
      System.out.println("NOT EXTIST");
      return;
    }
    Items k;
    if((k=C.removeItems(I))==null){
      System.out.println("Error");
      k.getTravelBug().getLog_t().insertLog(String.valueOf(k.getId()),"Transferio",String.valueOf(this.ID_UTI));
    }
    if(k.isTravel_bug()){
      k.getTravelBug().getHistory().add(this.ID_UTI);
    }
    this.addItems(k);
    this.log.insertLog(String.valueOf(C.getID_CACHE()),"Transferio",String.valueOf(this.ID_UTI));
    C.getD().insertLog(String.valueOf(C.getID_CACHE()),"Transferio",String.valueOf(this.ID_UTI));
  }

  public Items removeItems(Items I) {
    if (!this.isItems(I)) {
      System.out.println("Item:" + I.getName() + "Does not exist");
      return null;
    }
    this.Objects.remove(I);
    return I;

  }

  public void addItems(Items I) {
    if(I==null){
      System.out.println("NOT EXIST");
      return;
    }
    this.Objects.add(I);
  }

  public void isString() {
    ArrayList <Items> t=this.Objects;
    System.out.println(this.ID_UTI+this.Name+this.NumbVisted+this.getClass());
    for (Items items : t) {
      System.out.println(items.getName());
    }
  }



  public void Visite_cache(Cache G) {
    if(G.getType().compareTo("Premium")==0&&this.getClass()==Utilizador.class){
      this.log.insertLog(this.Name,"Tried Visite",String.valueOf(G.getID_CACHE()));
      System.out.println("ERRO CANT");
      return;
    }
if(this.NumbVisted==0||this.Visited_cache==null){
  this.Visited_cache=new ArrayList<>();
  this.Visited_cache.add(G.getID_CACHE());
 this.NumbVisted++;
 if (this.log==null) {
   this.log = new Util_log(new ArrayList<>());
 }
 this.log.insertLog(this.Name,"Visited",String.valueOf(G.getID_CACHE()));
  G.Visited_log_cache(this.ID_UTI);

 return;
}
    for (int i = 0; i <this.Visited_cache.size() ; i++) {
      if(this.Visited_cache.get(i)==G.getID_CACHE()){
        System.out.println("Already Visited");
        if (this.log==null){
          this.log=new Util_log(new ArrayList<>());
        }
        this.log.insertLog(this.Name,"Visited",String.valueOf(G.getID_CACHE()));
        G.Visited_log_cache(this.ID_UTI);
        return;
      }
    }
    this.Visited_cache.add(G.getID_CACHE());
    if (this.log==null){
      this.log=new Util_log(new ArrayList<>());

    }
    this.NumbVisted++;
    this.log.insertLog(this.Name,"Visited for the first time",String.valueOf(G.getID_CACHE()));
    G.Visited_log_cache(this.ID_UTI);

     }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public int getNumbVisted() {
    return NumbVisted;
  }

  public void setNumbVisted(int numbVisted) {
    NumbVisted = numbVisted;
  }

  public ArrayList<Items> getObjects() {
    return Objects;
  }

  public void setObjects(ArrayList<Items> objects) {
    Objects = objects;
  }

  public int getID_UTI() {
    return ID_UTI;
  }

  public void setID_UTI(int ID_UTI) {
    this.ID_UTI = ID_UTI;
  }

  public Util_log getLog() {
    return log;
  }

  public void setLog(Util_log log) {
    this.log = log;
  }

  public ArrayList<Integer> getVisited_cache() {
    return Visited_cache;
  }

  public void setVisited_cache(ArrayList<Integer> visited_cache) {
    Visited_cache = visited_cache;
  }
  public void not_visiteed(RedBlackBST_copy<Integer, Cache> stChe) {
    try {
if(this.getVisited_cache()==null){
  ArrayList<Integer> ar = new ArrayList<>();
  this.setVisited_cache(ar);
}
if(this.getVisited_cache().size()!=0) {
  for (Integer key:stChe.keys()) {
    int mode=0;
    for (int i = 0; i < this.Visited_cache.size() ; i++) {
      if(this.Visited_cache.get(i).equals(key)){
        mode=1;
    }
    }
    if(mode==0){
      System.out.println("Util: " + this.Name + "Nao visitou CacheN"+stChe.get(key).getID_CACHE()+"\n");
    }
  }
}else {
  System.out.println("Util: " + this.Name + "Nao visitou nelhuma cache");
}
    }catch (Exception a){
      a.printStackTrace();
    }
  }
  public Items Items_get_array(Integer a) {
    for (Items object : this.Objects) {
      if (a == object.getId()) {
        return object;
      }

    }
    return null;
  }
}