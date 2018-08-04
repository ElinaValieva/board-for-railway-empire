package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Optional;

@Log4j
public class DataManager {

    private static DataManager dataManager;

    public List<Schedule> schedules = Loader.getSchedules();

    private boolean CHANGE_VALUES_FLAG = false;

    private String LAST_CHANGE_MESSAGE = "";


    public List<TimeSchedule> loadScheduleDeparture(String selectedItem) {
        return Converter.conventDeparture(selectedItem, schedules);
    }

    public List<TimeSchedule> loadScheduleArrival(String selectedItem) {
        return Converter.convertArrival(selectedItem, schedules);
    }

    public List<Schedule> changeState() {
        return schedules = Loader.getSchedules();
    }

    public static DataManager getInstance() {
        if (dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    public boolean getStatusChanges() {
        return CHANGE_VALUES_FLAG;
    }

    public void resetStatusChanges() {
        CHANGE_VALUES_FLAG = false;
        LAST_CHANGE_MESSAGE = "";
    }

    public void upStatusChanges() {
        CHANGE_VALUES_FLAG = true;
    }

    public String getLastInfoChanges() {
        return LAST_CHANGE_MESSAGE;
    }

    public void setLastInfoChanges(String message) {
        LAST_CHANGE_MESSAGE = getMessageInfo(message);
    }

    public String getMessageInfo(String message) {
        final Long id = Long.valueOf(message.substring(message.indexOf("id=")).replace("id=", ""));
        Optional<Schedule> scheduleOptional = schedules.stream().filter(x -> x.getId().equals(id)).findFirst();
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            return "schedule between " + schedule.getStationDepartureName() + " - " + schedule.getStationArrivalName() + " was updated";
        }
        return null;
    }
}
