package elina.railwayApp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String URL_SCHEDULES = "http://localhost:8000/schedule/today";
    public static final String URL_STATIONS = "http://localhost:8000/station/auto/stations";
    public static final String URL_SCHEDULE_BY_ID = "http://localhost:8000/schedule/";


    public static String parseToTime(String date) throws ParseException {
        Date dateToday = parseToDateTime(date);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        return localDateFormat.format(dateToday);
    }

    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static Date parseToDateTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

}
