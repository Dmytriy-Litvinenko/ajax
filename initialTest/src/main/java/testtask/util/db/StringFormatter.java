package testtask.util.db;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormatter {

    public static Integer stringToInteger(String s) {
        Integer integer; //= null;
        //if (!s.equals(""))
        try{
            integer = Integer.valueOf(s);
        }catch (Exception e) {
            integer = null;
        }
        return integer;
    }

    public static Double stringToDouble(String s) {
        Double doubleValue;
        try {
            doubleValue = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            doubleValue = null;
        }
        return doubleValue;
    }

    public static Date stringToDate(String s){
        Date date ;
        //if (!s.equals(""))
        try {
            date = (new SimpleDateFormat("yyyy-mm-dd")).parse(s);
        } catch (ParseException e) {
            //e.printStackTrace();
            date=null;
        }
        return date;
    }

}
