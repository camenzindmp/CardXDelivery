/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class dateCounter { // get current date

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[]args) {

        Date date = new Date();
        System.out.println(sdf.format(date));

        Calendar calendar = Calendar.getInstance();
        System.out.println(sdf.format(calendar.getTime()));

        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        LocalDate localDate = LocalDate.now();
        System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
    }
}
*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCounter {

    public static void main(String[] args) {
        LocalDate date =  LocalDate.now().plusDays(7);
        System.out.println("Adding 7 day to current date: "+date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(date.format(formatter));
    }
}

