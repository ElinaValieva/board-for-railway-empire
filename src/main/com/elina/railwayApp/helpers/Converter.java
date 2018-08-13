package elina.railwayApp.helpers;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import elina.railwayApp.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    /**
     * @param stationMain
     * @param schedules
     * @return
     */
    public static List<TimeSchedule> conventDeparture(String stationMain, List<Schedule> schedules) {
        return schedules.stream()
                .filter(schedule -> schedule.getStationArrivalName().equals(stationMain) && Utils.checkScheduleForToday(schedule))
                .map(x -> {
                    TimeSchedule timeSchedule = new TimeSchedule();
                    timeSchedule.setStation(x.getStationDepartureName());
                    timeSchedule.setTrain(x.getTrainName());
                    timeSchedule.setTime(Utils.parseToTime(x.getDateDeparture()));
                    return timeSchedule;
                }).collect(Collectors.toList());
    }

    /**
     * @param stationMain
     * @param schedules
     * @return
     */
    public static List<TimeSchedule> convertArrival(String stationMain, List<Schedule> schedules) {
        return schedules.stream()
                .filter(schedule -> schedule.getStationDepartureName().equals(stationMain))
                .map(x -> {
                    TimeSchedule timeSchedule = new TimeSchedule();
                    timeSchedule.setStation(x.getStationArrivalName());
                    timeSchedule.setTrain(x.getTrainName());
                    timeSchedule.setTime(Utils.parseToTime(x.getDateArrival()));
                    return timeSchedule;
                }).collect(Collectors.toList());
    }
}
