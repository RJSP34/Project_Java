package Classimports;

public class Date {

  private int day;

  private int month;

  private int year;

  public Date() {

  }

  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;

  }

  public boolean beforeDate(Date d) {

    return this.compareTo(d)<0;
  }
    public void ToString() {
System.out.println(this.day+"/"+this.month+"/"+this.year);

    }
  public boolean isLeapYear() {
    if((this.year%400)==0){
      return true;
    }else if((this.year%4)==0&&(this.year%100)!=0){
      return true;
    }
    return false;
  }

  public boolean afterDate(Date d) {
    return this.compareTo(d)>0;
  }

  public void incrementDate() {

    }


  public int differenceYears(Date d) {

    return 0;
  }

  public int compareTo(Date teste) {
    if (this.year == teste.year) {
      if (this.month == teste.month) {
if (this.day==teste.day){
  return 0;
}else
        return (this.day - teste.day) / Math.abs(this.day - teste.day);

      } else {
        return (this.month - teste.month) / Math.abs(this.month - teste.month);
      }

    }else {
      return (this.year - teste.year) / Math.abs(this.year - teste.year);
    }


  }
  public static int daysBetweenMonths(short beginDay, short beginMonth, short endDay, short endMonth, int year){
    int difference=endMonth-beginMonth;
int nmrDias=0;
int diasFaltan=0;
    for (int i = 0; i <difference ; i++) {

    }
return 0;
  }
  public int differenceMonths(Date d) {
    return 0;
  }
public static int daysCrawler(Date begin, Date end){
    int d=end.year- begin.year;
    int soma=0;
  for (int i = 1; i <d ; i++) {
    Date c=begin;
    c.year=c.year+i;
if(c.isLeapYear()){
  soma=soma+366;
}else {

  soma=soma+365;
}
short bd= (short) begin.day;
    short bm= (short) begin.month;
    short cd= (short) end.day;
    short cs= (short) end.month;
soma=soma+ daysBetweenMonths(bd,bm,(short) 31, (short) 12, begin.year);
    soma=soma+ daysBetweenMonths((short) 1,(short) 1,cd, cs, end.year);
    return soma;
  }
  return 0;
}
 public  int daysMonth() {
    switch (this.month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 9:
      case 11:return 30;
      case 2:if(!this.isLeapYear()){
        return 28;
    }else return 29;
      default:return 31;
  }
  }
  public static void main(String[] args) {

  }


  public int diferenceYears(Date d2) {
return Math.abs(this.year-d2.year)*365 + Math.abs(this.month-d2.month)*30 +Math.abs(this.day-d2.day) ;

  }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}