import javax.lang.model.util.ElementScanner14;

public class Date{
  final  int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
  final String[] dayName={"sun","mon","tue","wed","thu","fri","sat"};
    int day;
    int month;
    int year;
  public Date(int day, int month, int year) {
      this.day = day;
      this.month = month;
      this.year = year;
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
  public void setDate(int day,int month,int year) {
      this.day = day;
      this.month= month;
      this.year = year;
  } 
  public String toString() {
      return "Date[("+day+","+month+","+year+")]"; 
  }
  public Date addDay(int num){
      int newDay=day+num;
      int newMonth=month;
      System.out.println("new day"+newDay+"new month"+newMonth);
      while(days[newMonth-1]<newDay){
          newDay=newDay-days[newMonth-1];
          System.out.println("deduct"+days[newMonth-1]+"days");
          newMonth++;
          System.out.println("new day"+newDay+"new month"+newMonth);
     }

     return new Date(newDay,newMonth,year);
    }
  public Date minDay(int num){
      int newDay=day-num;
      int newMonth;
      newMonth=newDay>0 ? month:month-1;
      while(newDay<0){
          newDay=newDay+days[newMonth-1];
       newMonth--;
         }
       return new Date(newDay,newMonth,year);
    }
    public int diff(Date nd) { //compaering this date and new date
        int diff=0;
        int smallmonth,largemonth;
        int smallday,largeday;
        if(this.month==nd.month && this.day==nd.day){
            return 0;
        }
        if(this.month==nd.month){
            return this.day - nd.day ;
        }
        smallmonth=this.month<nd.month ? this.month:nd.month;
        smallday=this.month<nd.day ? this.day:nd.day;
        largemonth=this.month>nd.month ? this.month:nd.month;
        largeday=this.month>nd.day ? this.day:nd.day;
        for (int i=smallmonth; i<largemonth; i++){
            if(i==smallmonth){
                diff=days[i-1]-smallday;
            }
            diff=diff+days[i-1]-smallday;
        }
        return diff+largeday;
    }
    
}
  
  
  
      