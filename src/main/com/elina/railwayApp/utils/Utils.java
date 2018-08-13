package elina.railwayApp.utils;

import elina.railwayApp.model.Schedule;
import lombok.extern.log4j.Log4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Log4j
public class Utils {

    public static final String URL_SCHEDULES = "http://localhost:8000/schedule/today";
    public static final String URL_STATIONS = "http://localhost:8000/station/auto/stations";
    public static final String URL_SCHEDULE_BY_ID = "http://localhost:8000/schedule/get/";


    public static String parseToTime(String date) {
        Date dateToday = parseToDateTime(date);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        return localDateFormat.format(dateToday);
    }

    public static Date getTodayDateTime() throws ParseException {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return parseToDate(df.format(date));
    }

    public static Date parseToDateTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static boolean checkScheduleForToday(Schedule schedule) {
        try {
            log.info("schedule " + schedule.getStationDepartureName() + " - " + schedule.getStationArrivalName() + " " + schedule.getDateDeparture() + " " + schedule.getDateArrival());
            Date dateArrival = parseToDate(schedule.getDateArrival());
            Date dateToday = getTodayDateTime();
            log.info("arrival" + dateArrival + " today" + dateToday + " status " + dateArrival.equals(dateToday));
            return dateArrival.equals(dateToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
