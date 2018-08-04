package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class DataManager {

    private List<Schedule> schedules = Loader.getSchedules();

    private String selectedItem;

    private static boolean CHANGE_VALUES_FLAG = false;

    private static String LAST_CHANGE_MESSAGE = "";

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;

    public List<TimeSchedule> updateScheduleDeparture(String selectedItem) {
        this.selectedItem = selectedItem;
        return schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
    }

    public List<TimeSchedule> updateScheduleArrival(String selectedItem) {
        this.selectedItem = selectedItem;
        return schedulesArrival = Converter.convertArrival(selectedItem, schedules);
    }

    public List<TimeSchedule> createScheduleDeparture(String selectedItem) {
        return schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
    }

    private void update(Long id) {
        Schedule schedule = Loader.getById(id);
        schedules.stream().filter(x -> x.getId().equals(id)).map(x -> x = schedule);
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
        LAST_CHANGE_MESSAGE = LAST_CHANGE_MESSAGE + "\n"
                + " update schedule between stations " + schedule.getStationArrivalName() + " - " + schedule.getStationDepartureName();
    }

    private void delete(Long id) {
        Schedule schedule = Loader.getById(id);
        schedules.remove(schedule);
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
        LAST_CHANGE_MESSAGE = LAST_CHANGE_MESSAGE + "\n"
                + " delete schedule between stations " + schedule.getStationArrivalName() + " - " + schedule.getStationDepartureName();
    }

    private void add(Long id) {
        Schedule schedule = Loader.getById(id);
        schedules.add(schedule);
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
        LAST_CHANGE_MESSAGE = LAST_CHANGE_MESSAGE + "\n"
                + " create new schedule between stations " + schedule.getStationArrivalName() + " - " + schedule.getStationDepartureName();
        log.info("UPDATE" + schedules.size());
    }

    public void changeState(String message, Long id) {
        if (message.contains("create"))
            add(id);

        else if (message.contains("delete"))
            delete(id);

        else update(id);
    }

    public List<TimeSchedule> createScheduleArrival(String selectedItem) {
        log.info("LOAD" + schedules.size());
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
        return schedulesArrival;
    }

    public static DataManager getInstance() {
        return new DataManager();
    }

    public static boolean getStatusChanges() {
        return CHANGE_VALUES_FLAG;
    }

    public static void resetStatusChanges() {
        if (CHANGE_VALUES_FLAG) {
            CHANGE_VALUES_FLAG = false;
            LAST_CHANGE_MESSAGE = "";
        } else CHANGE_VALUES_FLAG = true;
    }

    public static String getLastInfoChanges() {
        return LAST_CHANGE_MESSAGE;
    }
}
