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
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
public class ScheduleBean {
    private List<TimeSchedule> schedulesDeparture;
    private List<TimeSchedule> schedulesArrival;
    private String mainSchedule = "Ufa";
    private List<String> stations;
    private String selectedItem;

    public String getSelectedItem() {
        System.out.println(selectedItem);
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        System.out.println("set "+ selectedItem);
        this.selectedItem = selectedItem;
    }

    public List<String> getStations() throws IOException {
        stations = Loader.getStations();
        return stations;
    }

    @PostConstruct
    private void init() throws IOException {
        List<Schedule> schedules = Loader.getSchedules();
        schedulesDeparture = Converter.conventDeparture(mainSchedule, schedules);
        schedulesArrival = Converter.convertArrival(mainSchedule, schedules);
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
