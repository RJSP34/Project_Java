package Project;

import Classimports.Date;
import edu.princeton.cs.algs4.RedBlackBST;
import Classimports.RedBlackBST_copy;
import java.util.ArrayList;

public class Util_Admin extends Utilizador {
  private ArrayList<Integer> Owned;
  public Util_Admin(String name, int numbVisted, ArrayList<Items> objects, int ID_UTI, Util_log log,ArrayList<Integer> visited_cache,GPS loc,ArrayList<Integer> owned) {
    super(name, numbVisted, objects, ID_UTI,log,visited_cache,loc);
Owned=owned;
  }

  public void setCache(Cache C) {
  }

  public ArrayList<Integer> getOwned() {
    return Owned;
  }

  public void setOwned(ArrayList<Integer> owned) {
    Owned = owned;
  }

  public void removeCache( RedBlackBST_copy<Integer, Utilizador> BSTUtil, RedBlackBST_copy<Integer, Util_Admin> BSTADMIN, RedBlackBST_copy<Integer, Util_premium> BSTPREM,RedBlackBST_copy<Integer, Cache> stChe,int idc) {
    if (stChe.get(idc)==null) {
      System.out.println("Doesnt EXISTES");
      return;
    }
    for (int key : BSTUtil.keys()) {
      Utilizador aux = BSTUtil.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
            if (s.getTravelBug().getHistory_Ches().get(j).compareTo(idc) == 0) {
              s.getTravelBug().getHistory_Ches().remove(j);
              j--;
            }

          }

        }

      }
      for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
        if(aux.getVisited_cache().get(j).compareTo(idc)==0){
          aux.getVisited_cache().remove(j);
          j--;
        }
      }

    }
    for (int key : BSTPREM.keys()) {
      Util_premium aux = BSTPREM.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
            if (s.getTravelBug().getHistory_Ches().get(j).compareTo(idc) == 0) {
              s.getTravelBug().getHistory_Ches().remove(j);
              j--;
            }
          }
        }
      }
      for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
        if(aux.getVisited_cache().get(j).compareTo(idc)==0){
          aux.getVisited_cache().remove(j);
          j--;
        }
      }
        for (int j = 0; j <aux.getOwned().size() ; j++) {
          if(aux.getOwned().get(j)==idc){
            aux.getOwned().remove(j);
            j--;
          }
        }

    }
    for (int key : BSTADMIN.keys()) {
      Util_Admin aux = BSTADMIN.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
            if (s.getTravelBug().getHistory_Ches().get(j).compareTo(idc) == 0) {
              s.getTravelBug().getHistory_Ches().remove(j);
              j--;
            }

          }
        }

      }
      for (int j = 0; j <aux.getVisited_cache().size() ; j++) {
        if(aux.getVisited_cache().get(j).compareTo(idc)==0){
          aux.getVisited_cache().remove(j);
          j--;
        }
      }
      for (int j = 0; j <aux.getOwned().size() ; j++) {
        if(aux.getOwned().get(j)==idc){
          aux.getOwned().remove(j);
          j--;
        }
      }
    }
    for (int key:stChe.keys()) {
      Cache aux=stChe.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory_Ches().size(); j++) {
            if (s.getTravelBug().getHistory_Ches().get(j).compareTo(idc) == 0) {
              s.getTravelBug().getHistory_Ches().remove(j);
              j--;
            }

          }

        }
      }
    }
    stChe.delete(idc);
  }
  public void createtravelBug(Items I, Date inicial, Date limit,int U_D) {
    if(I.getTravel_bug()){
      System.out.println("ERRO: Item:"+I.getName()+"ja é um travel");
      return;
    }

    for (int i = 0; i <this.getObjects().size() ; i++) {
      if(I.getName().compareTo(this.getObjects().get(i).getName())==0){
        if(this.getObjects().get(i).getTravel_bug()){
          System.out.println("ERRO: Item:"+this.getObjects().get(i).getName()+"ja é um travel");
          return;
        }
        this.getObjects().get(i).setTravel_bug(true);
        ArrayList <Integer>aux2=new ArrayList<>();
        aux2.add(this.getID_UTI());

        TravelBug aux=new TravelBug(inicial,this.getID_UTI(),limit,aux2,null,U_D,new Travel_log(new ArrayList<>()));
        I.setTravelBug(aux);
        return;
      }
      System.out.println("ERRO: Item "+ I.getName()+"Not found");
    }

  }

  public Utilizador delet_Util(  int id,RedBlackBST_copy<Integer, Utilizador> BSTUtil, RedBlackBST_copy<Integer, Util_Admin> BSTADMIN, RedBlackBST_copy<Integer, Util_premium> BSTPREM, RedBlackBST_copy<Integer, Cache> stChe){
    if (!((BSTADMIN.get(id) != null && BSTPREM.get(id) == null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) != null && BSTUtil.get(id) == null)||(BSTADMIN.get(id) == null && BSTPREM.get(id) == null && BSTUtil.get(id) != null))) {
      System.out.println("Doesnt EXISTES");
      return null;
    }
    if(id==this.getID_UTI()){
      System.out.println("CAnt Remove Own");
      return null;
    }
    for (int key : BSTUtil.keys()) {
      Utilizador aux = BSTUtil.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
            if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
              s.getTravelBug().getHistory().remove(j);
              j--;
            }

          }

        }
      }
    }
    for (int key : BSTPREM.keys()) {
      Util_premium aux = BSTPREM.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
            if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
              s.getTravelBug().getHistory().remove(j);
              j--;
            }
          }
        }

      }

    }
    for (int key : BSTADMIN.keys()) {
      Util_Admin aux = BSTADMIN.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
            if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
              s.getTravelBug().getHistory().remove(j);
              j--;
            }

          }

        }
      }


    }
    for (int key:stChe.keys()) {
      Cache aux=stChe.get(key);
      for (int i = 0; i < aux.getObjects().size(); i++) {
        Items s = aux.getObjects().get(i);
        if (s.isTravel_bug()) {
          for (int j = 0; j < s.getTravelBug().getHistory().size(); j++) {
            if (s.getTravelBug().getHistory().get(j).compareTo(id) == 0) {
              s.getTravelBug().getHistory().remove(j);
              j--;
            }

          }

        }


      }
    }
    this.getLog().insertLog(String.valueOf(this.getID_UTI()),"Eliminou",String.valueOf(id));
    if(BSTADMIN.get(id)!=null){
      for (int i = 0; i <BSTADMIN.get(id).getOwned().size() ; i++) {
        if(id==BSTADMIN.get(id).getOwned().get(i)){
          BSTADMIN.get(id).getOwned().remove(i);
          i--;
        }
      }
      Util_Admin B=BSTADMIN.get(id);
      BSTADMIN.delete(id);
      return B;
    }else if(BSTPREM.get(id)!=null){
      for (int i = 0; i <BSTPREM.get(id).getOwned().size() ; i++) {
        if(id==BSTPREM.get(id).getOwned().get(i)){
          BSTPREM.get(id).getOwned().remove(i);
          i--;
        }
      }
      Util_premium B=BSTPREM.get(id);
      BSTPREM.delete(id);
      return B;
    }else {
      Utilizador B=BSTUtil.get(id);
      BSTUtil.delete(id);
      return B;
    }

  }
}