package Project;

import Classimports.Date;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class TravelBug {

  private Date Datetravelinicial;
  private int ActiveUlt;
  private int EndUlt;
  private Date Datetravelimit;
  private ArrayList<Integer> History;
  private ArrayList<Integer> History_Ches;
  private Travel_log Log_t;
  public TravelBug(Date datetravelinicial, int activeUlt, Date datetravelimit, ArrayList<Integer> history, ArrayList<Integer> history_Ches,int endult,Travel_log travel_log) {
    Datetravelinicial = datetravelinicial;
    ActiveUlt = activeUlt;
    Datetravelimit = datetravelimit;
    History = history;
    History_Ches=history_Ches;
    EndUlt=endult;
    Log_t=travel_log;
  }

  public Date getDatetravelinicial() {
    return Datetravelinicial;
  }

  public void setDatetravelinicial(Date datetravelinicial) {
    Datetravelinicial = datetravelinicial;
  }

  public int getActiveUlt() {
    return ActiveUlt;
  }

  public void setActiveUlt(int activeUlt) {
    ActiveUlt = activeUlt;
  }

  public Date getDatetravelimit() {
    return Datetravelimit;
  }

  public void setDatetravelimit(Date datetravelimit) {
    Datetravelimit = datetravelimit;
  }

  public ArrayList<Integer> getHistory() {
    return History;
  }

  public void setHistory(ArrayList<Integer> history) {
    History = history;
  }

  public int getEndUlt() {
    return EndUlt;
  }

  public void setEndUlt(int endUlt) {
    EndUlt = endUlt;
  }
/*
private Date Datetravelinicial;
  private int ActiveUlt;
  private int EndUlt;
  private Date Datetravelimit;
  private ArrayList<Integer> History;
  private ArrayList<Integer> History_Ches;
  private Travel_log Log_t;
 */
  public void now() {
System.out.println("ID de active :"+ this.ActiveUlt +"\n "+ "END:"+this.EndUlt +"\n");
    for (Integer integer : History) {
      System.out.println(integer);
    }
    for (Integer history_ch : History_Ches) {
      System.out.println(history_ch);
    }
this.Datetravelinicial.ToString();
    this.Datetravelimit.ToString();

  }

  public Travel_log getLog_t() {
    return Log_t;
  }

  public void setLog_t(Travel_log log_t) {
    Log_t = log_t;
  }

  public ArrayList<Integer> getHistory_Ches() {
    return History_Ches;
  }

  public void setHistory_Ches(ArrayList<Integer> history_Ches) {
    History_Ches = history_Ches;
  }
}