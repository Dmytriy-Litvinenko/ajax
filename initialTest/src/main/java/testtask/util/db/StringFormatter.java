package testtask.util.db;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormatter {

    public static Integer stringToInteger(String s){
        Integer integer = null;
        if(!s.equals(""))integer=Integer.valueOf(s);//employee.setSalary(null);
        return integer;
    }

    public static Date stringToDate(String s) throws ParseException {
        Date date=null;
        if (!s.equals("")) date=(new SimpleDateFormat("yyyy-mm-dd")).parse(s);
        return date;
    }

}
