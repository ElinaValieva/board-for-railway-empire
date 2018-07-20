package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
public class ScheduleBean {
    private List<Schedule> schedules;
    private List<TimeSchedule> schedulesDeparture;
    private List<TimeSchedule> schedulesArrival;
    private List<String> stations;
    private String selectedItem = "Saint Petersburg";

    public String getSelectedItem() {
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
        System.out.println("calc");
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getStations() throws IOException {
        stations = Loader.getStations();
        return stations;
    }

    @PostConstruct
    private void init() throws IOException {
        schedules = Loader.getSchedules();
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
    }

    public List<TimeSchedule> getSchedulesDeparture() {
        return schedulesDeparture;
    }

    public void setSchedulesDeparture(List<TimeSchedule> schedulesDeparture) {
        this.schedulesDeparture = schedulesDeparture;
    }

    public List<TimeSchedule> getSchedulesArrival() {
        return schedulesArrival;
    }

    public void setSchedulesArrival(List<TimeSchedule> schedulesArrival) {
        this.schedulesArrival = schedulesArrival;
    }
}