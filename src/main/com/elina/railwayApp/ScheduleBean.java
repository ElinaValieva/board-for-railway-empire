package elina.railwayApp;

import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@ManagedBean
@Singleton
@SessionScoped
@Log4j
public class ScheduleBean {

    private DataManager dataManager = DataManager.getInstance();

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;


    @Getter
    @Setter
    private List<String> stations = Loader.getStations();

    private String selectedItem = "Saint Petersburg";
    private Listener listener = new Listener();

    public String getSelectedItem() {
        return selectedItem;
    }


    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        schedulesDeparture = dataManager.updateScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.updateScheduleArrival(selectedItem);
    }

    @Schedule(second = "*/60", minute = "*", hour = "*", persistent = false)
    public void updateState() {
        schedulesDeparture = dataManager.updateScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.updateScheduleArrival(selectedItem);
        log.info("update @schedule ... ");
    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        schedulesDeparture = dataManager.createScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.createScheduleArrival(selectedItem);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }
}
