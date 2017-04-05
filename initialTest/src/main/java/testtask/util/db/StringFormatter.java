package testtask.util.db;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormatter {

    public static Integer stringToInteger(String s) {
        Integer integer = null;
        if (!s.equals("")) integer = Integer.valueOf(s);
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

    public static Date stringToDate(String s) throws ParseException {
        Date date = null;
        if (!s.equals("")) date = (new SimpleDateFormat("yyyy-mm-dd")).parse(s);
        return date;
    }

}
