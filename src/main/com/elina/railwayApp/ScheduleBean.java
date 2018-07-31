package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@ManagedBean
@SessionScoped
public class ScheduleBean {
    private List<Schedule> schedules;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;


    private List<String> stations;
    private String selectedItem = "Saint Petersburg";
    private Listener listener = new Listener();

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
    }

    public List<String> getStations() {
        stations = Loader.getStations();
        return stations;
    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        schedules = Loader.getSchedules();
        schedulesDeparture = Converter.conventDeparture(selectedItem, schedules);
        schedulesArrival = Converter.convertArrival(selectedItem, schedules);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }
}
