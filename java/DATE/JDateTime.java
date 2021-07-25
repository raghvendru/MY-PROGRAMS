import java.time.* ;
import java.time.format.DateTimeFormatter;
public class JDateTime{
    public static void main(String[] args) {
        LocalDate ldate = LocalDate.now() ;
        LocalDateTime ldatetime = LocalDateTime.now();
        LocalTime ltime = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E,dd/MMM/yyyy hh:mm a") ;
        System.out.println(ldate);
        System.out.println(ldate.format(dtf));
        System.out.println(ldatetime.format(dtf1));
        System.out.println(ltime);
        System.out.println(ldate.plusDays(30));
        System.out.println(LocalDate.parse("2020-11-11"));
    }
 }